package com.intellibucket.mock.payment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.intellibucket"})
public class PaymentMockServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMockServiceApplication.class, args);
    }
}