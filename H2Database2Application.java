package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling 
public class H2Database2Application {

	public static void main(String[] args) {
		SpringApplication.run(H2Database2Application.class, args);
	}

}