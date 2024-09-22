package com.intellibucket.user.service.domain.shell.handler2;

import com.intellibucket.model.UserRegistrationEntity;
import com.intellibucket.repository.UserRepository;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PasswordChangeServiceHandler {
    private final UserRepository userRepository;

    @Transactional
    public void changePassword(UUID userId, String oldPassword, String newPassword) {
        UserRegistrationEntity userRegistrationEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Password currentPassword = Password.of(userRegistrationEntity.getPassword());

        if (!currentPassword.isEqual(Password.of(oldPassword))) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        Password newPasswordObj = Password.changePassword(currentPassword, newPassword);

        userRegistrationEntity.setOldPassword(currentPassword.getValue());
        userRegistrationEntity.setPassword(newPasswordObj.getValue());

        userRepository.save(userRegistrationEntity);
    }
}