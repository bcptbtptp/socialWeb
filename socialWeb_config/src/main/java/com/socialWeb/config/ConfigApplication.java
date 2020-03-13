package com.socialWeb.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ClassName ConfigApplication
 * @Description TODO
 * @Author 42
 * @Date 2020/3/12 上午 11:30
 * @Version 1.0
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication
{
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
