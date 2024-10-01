package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.core.event.UserRegisteredEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.exception.user.UserValidationException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerCreateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerRegisterCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void handle(CustomerCreateCommand command) throws UserDomainException {
        UserRoot newUser = UserCommandMapper.customerCreateCommandToUserRoot(command);
        Optional<UserRoot> optionalUserRoot = userRepository.findByEmail(newUser.getEmail());

        if (optionalUserRoot.isPresent()) {
            throw new UserValidationException("User already exist with email..: " + command.getEmail());
        }
        UserRegisteredEvent userRegisteredEvent = userDomainService.customerRegistered(newUser);
        UserRoot savedUserRoot = userRepository.save(newUser);
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + newUser.getUserID());
        }
    }
}