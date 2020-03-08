package com.socialweb.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @ClassName SpitApplication
 * @Description 吐槽模块启动类
 * @Author 42
 * @Date 2020/3/8 下午 1:47
 * @Version 1.0
 */
@SpringBootApplication
public class SpitApplication
{
	public static void main(String[] args){
		SpringApplication.run(SpitApplication.class);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}
}
