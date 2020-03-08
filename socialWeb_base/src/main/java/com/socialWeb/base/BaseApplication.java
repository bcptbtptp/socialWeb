package com.socialWeb.base;

import java.sql.DatabaseMetaData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @ClassName BaseApplication
 * @Description TODO
 * @Author 42
 * @Date 2020/3/6 下午 3:42
 * @Version 1.0
 */
@SpringBootApplication
public class BaseApplication
{
	public static void main(String[] args){
		SpringApplication.run(BaseApplication.class,args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}
}
