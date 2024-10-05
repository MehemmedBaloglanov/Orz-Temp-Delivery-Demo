package com.intellibucket.user.service.domain.shell.handler.command;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.request.UserLoginCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserLoginCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void handle(UserLoginCommand command) throws UserDomainException {
        UserRoot userFromCommand = UserCommandMapper.userLoginCommandToUserRoot(command);
        System.out.println("userFromCommand: --1 " + userFromCommand);
        // Retrieve user by email based on a role
        Optional<UserRoot> userRootOptional = userRepository.findByEmailForLogin(userFromCommand.getEmail());
        System.out.println("userRootOptional: --3 " + userRootOptional);
        // Check if user exists
        if (userRootOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + command.getEmail());
        }

        Password inputPassword = Password.of(command.getPassword());
        if (!userFromCommand.getPassword().equals(inputPassword)) {
            throw new PasswordValidationException("Try again!");
        }
        // Authenticate the user using Spring Security
        Authentication authentication = new UsernamePasswordAuthenticationToken(userFromCommand.getEmail(), null, new ArrayList<>());

        // Set authentication in SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Authentication successful for user: " + userFromCommand.getEmail());
        UserRoot user = userRootOptional.get();
//        user.setLastLoginDate(OffsetDateTime.now());
        userRepository.loginUser(user);
        System.out.println("User saved: " + user.toString());
    }
}