package com.myask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.myask.mapper")
@SpringBootApplication
public class MyAskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAskApplication.class, args);
	}
}
