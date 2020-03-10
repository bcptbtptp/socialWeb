package com.socialWeb.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServer
 * @Description TODO
 * @Author 42
 * @Date 2020/3/10 下午 1:54
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer
{
	public static void main(String[] args){
		SpringApplication.run(EurekaServer.class, args);
	}
}
