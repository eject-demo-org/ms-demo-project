package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan(basePackages = {"com.demo"})
@ComponentScan({"com.demo"})
public class UserDataProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataProjectApplication.class, args);
	}

	
}
