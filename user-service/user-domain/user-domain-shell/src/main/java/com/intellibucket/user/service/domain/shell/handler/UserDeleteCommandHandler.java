package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.response.UserDeleteResponse;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDeleteCommandHandler {
    private final UserRepository userRepository;

    public void handle(UserID userID) throws UserDomainException {
        Optional<UserRoot> userRootOptional = userRepository.findByUserId(userID);
        if (userRootOptional.isEmpty()) {
            throw new UserNotFoundException("user not found with id: " + userID.value());
        }
        UserRoot userRoot = userRootOptional.get();
        userRoot.deactivate();
        userRepository.save(userRoot);
    }
}
