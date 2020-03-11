package com.socialWeb.friend.controller;
import com.socialWeb.friend.service.FriendService;
import entity.StatusCode;

import entity.Result;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;

/**
 * @ClassName FriendController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 上午 9:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/friend")
public class FriendController
{
//	@Autowired
//	private JwtUtil jwtUtil;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private FriendService friendService;

	@RequestMapping(value = "/like/{friendId}/{type}", method = RequestMethod.PUT)
	public Result addFriend(@PathVariable String friendId, @PathVariable String type){
		Claims claims = (Claims)request.getAttribute("claims_user");
		if (claims == null){
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		String userId = claims.getId();
		if (type != null){
			if ("1".equals(type)){
				if (0 == friendService.addFriend(userId, friendId)){
					return new Result(false, StatusCode.ERROR, "不能重复添加");
				}
			}else if ("2".equals(type)){

			}else{
				return new Result(false, StatusCode.ERROR, "参数异常");
			}
		}


		return new Result(true, StatusCode.OK, "添加成功");
	}


}
