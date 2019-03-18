package com.andrei.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}

}
