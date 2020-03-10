package com.socialWeb.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @InterfaceName BaseClient
 * @Description TODO
 * @Author 42
 * @Date 2020/3/10 下午 5:04
 * @Version 1.0
 */
@FeignClient(name = "socialWeb-base")
public interface BaseClient
{
	@RequestMapping(value="/label/{id}", method = RequestMethod.GET)
	public Result findById(@PathVariable("id") String id);
}
