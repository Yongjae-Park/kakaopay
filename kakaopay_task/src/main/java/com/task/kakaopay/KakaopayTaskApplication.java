package com.task.kakaopay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.task.kakaopay.mapper")
public class KakaopayTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaopayTaskApplication.class, args);
	}

}
