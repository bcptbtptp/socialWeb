package com.socialWeb.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName BaseExceptionHandler
 * @Description Base exception handler
 * @Author 42
 * @Date 2020/3/7 下午 3:02
 * @Version 1.0
 */
@RestControllerAdvice
public class BaseExceptionHandler
{
	@ExceptionHandler(value = Exception.class)
	public Result exception(Exception ex){
		ex.printStackTrace();
		return new Result(false, StatusCode.ERROR,ex.getMessage());
	}
}
