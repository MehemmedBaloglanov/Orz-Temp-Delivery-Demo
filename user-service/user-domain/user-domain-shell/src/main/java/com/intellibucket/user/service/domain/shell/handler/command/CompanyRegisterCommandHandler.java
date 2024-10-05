package com.intellibucket.user.service.domain.shell.handler.command;

import com.intellibucket.user.service.domain.core.event.UserRegisteredEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.email.EmailAlreadyExistException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyCreateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.notification.EmailService;
import com.intellibucket.user.service.domain.shell.port.output.publisher.EventPublisher;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRegisterCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final EventPublisher eventPublisher;
    private final EmailService emailService;

    public void handle(CompanyCreateCommand command) throws UserDomainException {
        UserRoot newUser = UserCommandMapper.companyCreateCommandToUserRoot(command);
        Optional<UserRoot> optionalUserRoot = userRepository.findByEmail(newUser.getEmail(), newUser);

        if (optionalUserRoot.isPresent()) {
            throw new EmailAlreadyExistException("User already exist with email..: " + command.getEmail());
        }

        UserRoot savedUserRoot = userRepository.save(newUser);
        emailService.sendRegisteringMessage(command.getEmail(), command.getCompanyName());
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + newUser.getUserID());
        }

        UserRegisteredEvent userRegisteredEvent = userDomainService.companyRegistered(newUser);
        eventPublisher.publishUserRegisteredEvent(userRegisteredEvent);
    }
} //outbox-a save et userRegisteredEvent, sonra scheduler ile mueyyen vaxtdan bir meulumarlari cekib kafkaya push et