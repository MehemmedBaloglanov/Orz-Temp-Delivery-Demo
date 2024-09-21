package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerCreateCommand;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomerRegisterCommandHandler {
    private final UserRepository userRepository;
    private final AbstractUserCommandService commandService;

    public void handle(CustomerCreateCommand command) throws UserDomainException {
        UserID userID = UserID.random();
        Optional<UserRoot> optionalUserRoot = userRepository.findByUserId(userID);

        if (optionalUserRoot.isEmpty()) {
            throw new UserNotFoundException("User already exist: " + userID.value());
        }
        commandService.customerRegistered(command);
        userRepository.save(optionalUserRoot.get());

    }

}
