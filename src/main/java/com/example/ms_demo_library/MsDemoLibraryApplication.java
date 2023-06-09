package com.example.ms_demo_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsDemoLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDemoLibraryApplication.class, args);
	}

}
