package com.andrei.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public ZuulPreFilter preFilter() {
        return new ZuulPreFilter();
    }

    @Bean
    public ZuulPostFilter postFilter() {
        return new ZuulPostFilter();
    }

    @Bean
    public ZuulErrorFilter errorFilter() {
        return new ZuulErrorFilter();
    }

    @Bean
    public ZuulRouteFilter routeFilter() {
        return new ZuulRouteFilter();
    }


    @Bean
    public ZuulLoggingFilter loggingFilter() {
        return new ZuulLoggingFilter();
    }
}
