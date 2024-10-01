package com.intellibucket.user.service.configuration;

import com.intellibucket.user.service.domain.core.service.adapter.UserDomainServiceImpl;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainServiceImpl();
    }
}
