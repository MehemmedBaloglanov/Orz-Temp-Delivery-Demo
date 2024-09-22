package com.intellibucket.user.service.domain.shell.handler2;

import com.intellibucket.model.UserRegistrationEntity;
import com.intellibucket.repository.UserRepository;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteServiceHandler {
    private final UserRepository userRepository;

    public void markUserAsInactive(String userId) {
        UUID uuid = UUID.fromString(userId);

        Optional<UserRegistrationEntity> optionalUser = userRepository.findById(uuid);

        if (optionalUser.isPresent()) {
            UserRegistrationEntity userRegistrationEntity = optionalUser.get();

            userRegistrationEntity.setStatus(Status.DELETED);

            userRepository.save(userRegistrationEntity);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}