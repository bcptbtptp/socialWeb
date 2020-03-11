package com.socialWeb.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @InterfaceName UserClient
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 4:39
 * @Version 1.0
 */
@FeignClient
public interface UserClient
{
	/**
	 * 增加粉丝数
	 * @param userid
	 * @param x
	 */
	@RequestMapping(value="/user/incfans/{userid}/{x}", method = RequestMethod.POST)
	public void incFanscount(@PathVariable("userid") String userid, @PathVariable("x") int x);
	/**
	 * 增加关注数
	 * @param userid
	 * @param x
	 */
	@RequestMapping(value="/user/incfollow/{userid}/{x}", method = RequestMethod.POST)
	public void incFollowcount(@PathVariable("userid") String userid,@PathVariable("x") int x);
}
