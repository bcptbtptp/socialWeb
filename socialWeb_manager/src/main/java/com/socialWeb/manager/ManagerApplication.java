package com.socialWeb.manager;
import	java.beans.Beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

/**
 * @ClassName ManagerApplication
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 5:28
 * @Version 1.0
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ManagerApplication
{
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}
