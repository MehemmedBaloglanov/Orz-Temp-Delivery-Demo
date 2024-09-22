package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserValidationException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerCreateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
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
        UserRoot newUser = UserCommandMapper.customerCreateCommandToUserRoot(command);

        Optional<UserRoot> optionalUserRoot = userRepository.findByEmail(newUser.getEmail());

        if (optionalUserRoot.isPresent()) {
            throw new UserValidationException("User already exist with email..: " + command.getEmail());
        }


        userRepository.save(newUser);
        commandService.customerRegistered(command);

    }

}
