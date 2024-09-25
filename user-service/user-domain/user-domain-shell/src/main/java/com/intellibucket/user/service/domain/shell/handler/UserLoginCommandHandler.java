package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.UserDomainService;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.request.UserLoginCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserLoginCommandHandler {
    private final UserRepository userRepository;
    private final AbstractUserCommandService commandService;
    private final UserDomainService userDomainService;

    public void handle(UserLoginCommand command) throws UserDomainException {
        UserRoot userFromCommand = UserCommandMapper.userLoginCommandToUserRoot(command);

        Optional<UserRoot> userRootOptional = userRepository.findByEmail(userFromCommand.getEmail());

        if (userRootOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with email! " + command.getEmail());
        }

        UserRoot user = userRootOptional.get();

        if (!user.getPassword().equals(Password.builder().value(command.getPassword()).build())) {
            throw new PasswordValidationException("Invalid credentials!");
        }

        userDomainService.userLoggedIn(userFromCommand);

        commandService.userLoggedIn(command);

    }
}
