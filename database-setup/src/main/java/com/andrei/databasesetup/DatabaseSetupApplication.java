package com.andrei.databasesetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DatabaseSetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseSetupApplication.class, args);
    }

}
