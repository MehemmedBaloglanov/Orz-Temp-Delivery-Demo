package com.intellibucket.user.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserChangePasswordDomainEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.request.UserChangePasswordCommand;
import com.intellibucket.user.service.domain.shell.port.output.publisher.EventPublisher;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangePasswordCommandHandler {
    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;
    private final UserDomainService userDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    public void handle(UserChangePasswordCommand command) throws UserDomainException {

        UserID userID = securityContextHolder.currentUserID();

        Optional<UserRoot> userRoot = userRepository.findByUserId(userID);
        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userID);
        }
        UserRoot user = userRoot.get();

        Password oldPassword = Password.builder().value(command.getOldPassword()).build();
        Password newPassword = Password.builder().value(command.getNewPassword()).build();

        user.changePassword(oldPassword, newPassword);

        UserChangePasswordDomainEvent userChangePasswordDomainEvent = userDomainService.userChangePassword(user);
        eventPublisher.publishUserChangePasswordEvent(userChangePasswordDomainEvent);

        UserRoot savedUserRoot = userRepository.save(user);
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + user.getUserID());
        }
    }
}