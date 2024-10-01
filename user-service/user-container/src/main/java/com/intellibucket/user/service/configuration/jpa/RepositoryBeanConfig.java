package com.intellibucket.user.service.configuration.jpa;


import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.repository.adapter.UserRepositoryImpl;
import com.intellibucket.user.service.repository.mapper.UserDataAccessMapper;
import com.intellibucket.user.service.repository.repository.CompanyJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.intellibucket.user.service.repository.repository")
public class RepositoryBeanConfig {
    private final CompanyJpaRepository companyJpaRepository;

    public RepositoryBeanConfig(CompanyJpaRepository companyJpaRepository) {
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