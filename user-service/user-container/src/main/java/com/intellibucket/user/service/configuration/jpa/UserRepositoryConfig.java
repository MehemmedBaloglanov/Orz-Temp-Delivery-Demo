package com.intellibucket.user.service.configuration.jpa;

import com.intellibucket.adapter.UserRepositoryImpl;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.repository.UserJpaRepository;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserRepositoryConfig {
    private UserDataAccessMapper userDataAccessMapper;
    private UserJpaRepository userJpaRepository;

    public UserRepositoryConfig(UserDataAccessMapper userDataAccessMapper, UserJpaRepository userJpaRepository) {
        this.userDataAccessMapper = userDataAccessMapper;
        this.userJpaRepository = userJpaRepository;
    }

    @Bean
    public UserRepository userRepository() {
        // Instantiate manually if needed (depending on implementation)
        return new UserRepositoryImpl(userDataAccessMapper, userJpaRepository);
    }
}