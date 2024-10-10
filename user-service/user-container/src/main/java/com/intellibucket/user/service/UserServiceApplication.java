package com.intellibucket.user.service;

import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import com.intellibucket.user.service.repository.model.PhoneNumberEntity;
import com.intellibucket.user.service.repository.model.UserAddressEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.intellibucket.user.service.repository.repository"})
@EntityScan(basePackageClasses = {CompanyRegistrationEntity.class, CustomerRegistrationEntity.class, PhoneNumberEntity.class, UserAddressEntity.class})
@ComponentScan(basePackages = {"com.intellibucket.user.service", "publisher"})
@SpringBootApplication(scanBasePackages = {"com.intellibucket.user.service"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}