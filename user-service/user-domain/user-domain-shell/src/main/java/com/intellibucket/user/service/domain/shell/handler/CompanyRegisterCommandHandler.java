package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.core.event.UserRegisteredEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.exception.user.UserValidationException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyCreateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRegisterCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void handle(CompanyCreateCommand command) throws UserDomainException {
        System.out.println("Step 1: Start handling CompanyCreateCommand");

        UserRoot newUser = UserCommandMapper.companyCreateCommandToUserRoot(command);
        System.out.println("Step 2: User mapped from CompanyCreateCommand - Email: " + newUser.getEmail().getValue());

        Optional<UserRoot> userRoot = userRepository.findByEmail(newUser.getEmail());
        System.out.println("Step 3: Checking if user exists by email");

        if (userRoot.isPresent()) {
            System.out.println("Step 4: User already exists with email: " + command.getEmail());
            throw new UserValidationException("User already exist with email..: " + command.getEmail());
        }

        UserRegisteredEvent userRegisteredEvent = userDomainService.companyRegistered(newUser);
        System.out.println("Step 5: Domain event triggered for user registration");

        UserRoot savedUserRoot = userRepository.save(newUser);
        System.out.println("Step 6: New user saved in repository");

        if (savedUserRoot == null) {
            System.out.println("Step 7: Error saving user");
            throw new UserSavedException("User could not be saved: " + newUser.getUserID());
        }
        System.out.println("Step 8: User created successfully");
    }
}
//outbox-a save et userRegisteredEvent
//sonra scheduler ile mueyyen vaxtdan bir meulumarlari cekib kafkaya push et