package com.intellibucket.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.intellibucket.user.service.repository.repository"})
//@EntityScan(basePackageClasses = {CompanyRegistrationEntity.class, CustomerRegistrationEntity.class, PhoneNumberEntity.class, UserAddressEntity.class})
@ComponentScan(basePackages = {"com.intellibucket.user.service", "publisher"})
@SpringBootApplication(scanBasePackages = {"com.intellibucket.user.service.repository.repository", "com.intellibucket.user.service"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}