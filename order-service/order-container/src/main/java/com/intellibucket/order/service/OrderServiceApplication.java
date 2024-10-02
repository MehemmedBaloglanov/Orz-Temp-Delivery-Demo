package com.intellibucket.order.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.intellibucket.order.service",
        "com.intellibucket.user.service.connector",
        "com.intellibucket.company.service.connector",
        "com.intellibucket.cart.service.connector",
        "com.intellibucket.outbox",
        "com.intellibucket.kafka",
        "com.intellibucket.domain"

})
@EnableJpaRepositories("com.intellibucket.order.service.repository.repository")
@EntityScan("com.intellibucket.order.service.repository.model")
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
