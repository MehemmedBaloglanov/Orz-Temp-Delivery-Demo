package com.intellibucket.mock.delivery.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.intellibucket"})
public class DeliveryMockServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryMockServiceApplication.class, args);
    }
}