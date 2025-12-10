package com.zabora.user_activity_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClient
public class UserActivityServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserActivityServiceApplication.class, args);
	}
	

}
