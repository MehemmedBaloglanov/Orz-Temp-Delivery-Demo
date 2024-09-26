package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserValidationException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyCreateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class CompanyRegisterCommandHandler {
    private final UserRepository userRepository;
    private final AbstractUserCommandService commandService;
    private final UserDomainService userDomainService;

    public void handle(CompanyCreateCommand command) throws UserDomainException {
        UserRoot newUser = UserCommandMapper.companyCreateCommandToUserRoot(command);
        Optional<UserRoot> optionalUserRoot = userRepository.findByEmail(newUser.getEmail());

        if (optionalUserRoot.isPresent()) {
            throw new UserValidationException ("User already exist with email..: " + command.getEmail());
        }

        userDomainService.companyRegistered(newUser);
        commandService.companyRegistered(command);
        userRepository.save(newUser);
    }


    }

