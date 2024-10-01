package com.intellibucket.user.service.configuration.jpa;

import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.repository.adapter.UserRepositoryImpl;
import com.intellibucket.user.service.repository.mapper.UserDataAccessMapper;
import com.intellibucket.user.service.repository.repository.CompanyJpaRepository;
import com.intellibucket.user.service.repository.repository.CustomerJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableJpaRepositories(basePackages = "com.intellibucket.user.service.repository.repository")
public class RepositoryBeanConfig {

    private final CompanyJpaRepository companyJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;

    public RepositoryBeanConfig(CompanyJpaRepository companyJpaRepository, CustomerJpaRepository customerJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
    }

    @Bean
    public UserDataAccessMapper userDataAccessMapper() {
        return new UserDataAccessMapper();
    }

    @Bean
    public UserRepository userRepository(UserDataAccessMapper userDataAccessMapper) {
        return new UserRepositoryImpl(userDataAccessMapper, customerJpaRepository, companyJpaRepository);
    }
}