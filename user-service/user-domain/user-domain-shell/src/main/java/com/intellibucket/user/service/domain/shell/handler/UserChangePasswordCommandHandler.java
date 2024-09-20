package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.request.UserChangePasswordCommand;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangePasswordCommandHandler {
    private final AbstractUserCommandService abstractUserCommandService;
    private final UserRepository userRepository;

    public void handle(UserChangePasswordCommand command) throws PasswordValidationException, UserNotFoundException {
        UserID userID = UserID.random(); // FIXME userID securityContextHolder'dan alinacaq
        Optional<UserRoot> byUserId = abstractUserCommandService.findByUserId(userID);

        if (byUserId.isEmpty()) {
            throw new UserNotFoundException("user not found with id:" + userID);
        }
        UserRoot user = byUserId.get();

        if (!user.getPassword().equals(Password.builder().value(command.getOldPassword()).build())) {
            throw new PasswordValidationException("old password is invalid!");
        }

        Password newPassword = Password.builder().value(command.getNewPassword()).build();
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
