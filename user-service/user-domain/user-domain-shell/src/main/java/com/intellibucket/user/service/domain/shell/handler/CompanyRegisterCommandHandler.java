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
import publisher.KafkaEventPublisher;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRegisterCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final KafkaEventPublisher eventPublisher;

    public void handle(CompanyCreateCommand command) throws UserDomainException {
        UserRoot newUser = UserCommandMapper.companyCreateCommandToUserRoot(command);
        Optional<UserRoot> userRoot = userRepository.findByEmail(newUser.getEmail());

        if (userRoot.isPresent()) {
            throw new UserValidationException("User already exist with email..: " + command.getEmail());
        }
        UserRegisteredEvent userRegisteredEvent = userDomainService.companyRegistered(newUser);
//        eventPublisher.publishEvent(userRegisteredEvent);
        UserRoot savedUserRoot = userRepository.save(newUser);
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + newUser.getUserID());
        }
    }
} //outbox-a save et userRegisteredEvent, sonra scheduler ile mueyyen vaxtdan bir meulumarlari cekib kafkaya push et