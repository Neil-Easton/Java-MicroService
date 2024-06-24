package com.example.book;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer
public class Book {

	public static void main(String[] args) {
		SpringApplication.run(Book.class, args);
	}

}
