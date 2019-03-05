package com.andrei.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CategoryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryApiApplication.class, args);
	}

}
