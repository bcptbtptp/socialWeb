package com.socialWeb.user.controller;
import	java.awt.font.NumericShaper.Range;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.Oneway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialWeb.user.pojo.User;
import com.socialWeb.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@RefreshScope
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value="/incfollow/{userid}/{x}",method=
			RequestMethod.POST)
	public void incFollowcount(@PathVariable String userid,@PathVariable int x){
		userService.incFollowcount(userid,x);
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody User user){
		user = userService.login(user.getMobile(), user.getPassword());
		if (null == user){
			return new Result(false, StatusCode.ERROR, "登录失败");
		}
		String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("roles", "user");
		return new Result(true, StatusCode.OK, "登录成功", map);
	}

	@RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
	public Result register(@PathVariable String code, @RequestBody User user){
		String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkCode_" + user.getMobile());
		if (checkcodeRedis.isEmpty()){
			return new Result(false, StatusCode.ERROR, "请先用手机获取验证码");
		}
		if (!checkcodeRedis.equals(code)){
			return new Result(false, StatusCode.ERROR, "验证码错误");
		}
		userService.add(user);
		return new Result(true, StatusCode.OK, "注册成功");
	}

	/**
	 * 发送短信验证码
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
	public Result sendSms(@PathVariable String mobile){
		userService.sendSms(mobile);
		return new Result(true, StatusCode.OK,"发送成功");
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
