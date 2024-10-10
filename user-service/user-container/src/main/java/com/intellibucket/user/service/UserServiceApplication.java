package com.intellibucket.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.intellibucket.user.service.repository.repository"})
@EntityScan(basePackages = {"com.intellibucket.user.service.repository.model"})
@SpringBootApplication(scanBasePackages = {"com.intellibucket.user.service"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}