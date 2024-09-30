package com.intellibucket.user.service;

import com.intellibucket.model.CompanyRegistrationEntity;
import com.intellibucket.model.CustomerRegistrationEntity;
import com.intellibucket.model.PhoneNumberEntity;
import com.intellibucket.model.UserAddressEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = {"com.intellibucket.repository"})
//@EnableJpaRepositories(basePackages = {"com.intellibucket.user.service.domain.shell.port.output.repository"})
@EntityScan(basePackageClasses = {CompanyRegistrationEntity.class, CustomerRegistrationEntity.class, PhoneNumberEntity.class, UserAddressEntity.class})
@SpringBootApplication(scanBasePackages = {"com.intellibucket.repository", "com.intellibucket.user.service"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}