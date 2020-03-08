package com.socialweb.spit.controller;

import com.socialweb.spit.pojo.Spit;
import com.socialweb.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SpitController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 1:59
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController
{

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private SpitService spitService;

	@RequestMapping(method = RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功", spitService.findAll());
	}

	@RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String spitId){
		return new Result(true, StatusCode.OK,"查询成功", spitService.findById(spitId));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Spit spit){
		spitService.save(spit);
		return new Result(true, StatusCode.OK,"保存成功");
	}

	@RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String spitId, @RequestBody Spit spit){
		spit.set_id(spitId);
		spitService.save(spit);
		return new Result(true, StatusCode.OK,"更新成功");
	}

	@RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String spitId){
		spitService.deleteById(spitId);
		return new Result(true, StatusCode.OK,"删除成功");
	}

	@RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
	public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
		Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
	}

	@RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
	public Result thumbup(@PathVariable String spitId){
		String userId = "111";
		//判断是否已经点赞
		if (null != redisTemplate.opsForValue().get("thumbup_" + userId)){
			return new Result(true, StatusCode.ERROR, "不能重复点赞");
		}
		spitService.thumbup(spitId);
		redisTemplate.opsForValue().set("thumbup_" + userId, 1);
		return new Result(true, StatusCode.OK, "点赞成功", spitService.findById(spitId));
	}
}
