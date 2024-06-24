package com.example.university;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer
public class University {

	public static void main(String[] args) {
		SpringApplication.run(University.class, args);
	}

}
