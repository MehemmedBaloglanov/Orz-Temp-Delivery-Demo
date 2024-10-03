package com.intellibucket.user.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.user.service.domain.core.event.UserChangePasswordDomainEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.request.UserChangePasswordCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import publisher.KafkaEventPublisher;

@Component
@RequiredArgsConstructor
public class UserChangePasswordCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final KafkaEventPublisher eventPublisher;

    public void handle(UserChangePasswordCommand command) throws UserDomainException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Username userNameValueObject = Username.of(username);

        UserRoot userRoot = userRepository.findByUsername(userNameValueObject)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        String currentPassword = userRoot.getPassword().getValue(); // Assuming Password has a getValue() method

        Password newPassword = Password.changePassword(currentPassword, command.getOldPassword(), command.getNewPassword());

        UserChangePasswordDomainEvent userChangePasswordDomainEvent = userDomainService.userChangePassword(userRoot);

        // Update the userRoot with the new password
        userRoot.userChangePassword(newPassword);

        userRepository.save(userRoot);
    }
}