package com.intellibucket.order.service;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class OrderServiceApplication {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
