package com.intellibucket.user.service.configuration.jpa;

import com.intellibucket.adapter.UserRepositoryImpl;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.repository.CompanyJpaRepository;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserRepositoryConfig {
    private UserDataAccessMapper userDataAccessMapper;
    private CompanyJpaRepository companyJpaRepository;

    public UserRepositoryConfig(UserDataAccessMapper userDataAccessMapper,
                                CompanyJpaRepository companyJpaRepository) {
        this.userDataAccessMapper = userDataAccessMapper;
        this.companyJpaRepository = companyJpaRepository;
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(userDataAccessMapper, companyJpaRepository);
    }
}