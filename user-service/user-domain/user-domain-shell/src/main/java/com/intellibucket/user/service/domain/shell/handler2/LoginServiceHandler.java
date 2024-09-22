package com.intellibucket.user.service.domain.shell.handler2;

import com.intellibucket.model.UserRegistrationEntity;
import com.intellibucket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class LoginServiceHandler {
    private final UserRepository userRepository;
    public boolean userLoggedIn(String email, String password) {
        Optional<UserRegistrationEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserRegistrationEntity user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}