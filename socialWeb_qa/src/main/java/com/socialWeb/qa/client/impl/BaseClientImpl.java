package com.socialWeb.qa.client.impl;

import com.socialWeb.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @ClassName BaseClientImpl
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 4:52
 * @Version 1.0
 */
@Component
public class BaseClientImpl implements BaseClient
{

	@Override
	public Result findById(String id) {
		return new Result(false, StatusCode.ERROR, "熔断触发");
	}
}
