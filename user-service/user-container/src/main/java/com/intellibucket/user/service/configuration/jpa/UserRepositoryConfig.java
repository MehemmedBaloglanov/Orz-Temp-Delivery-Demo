package com.intellibucket.user.service.configuration.jpa;

import com.intellibucket.adapter.UserRepositoryImpl;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.repository.CompanyJpaRepository;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.intellibucket.repository")
public class UserRepositoryConfig {
    private final CompanyJpaRepository companyJpaRepository;

    public UserRepositoryConfig(CompanyJpaRepository companyJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
    }

    @Bean
    public UserDataAccessMapper userDataAccessMapper() {
        return new UserDataAccessMapper();  // Create and return the UserDataAccessMapper instance
    }

    @Bean
    public UserRepository userRepository(UserDataAccessMapper userDataAccessMapper) {
        return new UserRepositoryImpl(userDataAccessMapper, companyJpaRepository);
    }
}