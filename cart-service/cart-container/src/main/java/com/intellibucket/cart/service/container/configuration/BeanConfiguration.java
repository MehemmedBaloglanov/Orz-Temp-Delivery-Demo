package com.intellibucket.cart.service.container.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CartServiceRepository cartServiceRepository() {
        return new CartServiceRepositoryImpl();
    }
}
