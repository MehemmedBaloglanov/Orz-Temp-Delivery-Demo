package com.intellibucket.mock.company.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.intellibucket"})
public class CompanyMockServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyMockServiceApplication.class, args);
    }
}
