package com.tacos.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
    //todo resolve Access-Control-Allow-Origin duplicate header
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}