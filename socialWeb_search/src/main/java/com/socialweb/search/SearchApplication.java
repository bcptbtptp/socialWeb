package com.socialweb.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @ClassName SearchApplication
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 6:40
 * @Version 1.0
 */
@SpringBootApplication
public class SearchApplication
{
	public static void main(String[] args){
		SpringApplication.run(SearchApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}
}
