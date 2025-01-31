package com.intellibucket.user.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserUpdatedDomainEvent;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyUpdateCommand;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerUpdateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.publisher.EventPublisher;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerUpdateCommandHandler {
    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;
    private final UserDomainService userDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    @Transactional
    public void handle(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {

        UserID userID = securityContextHolder.currentUserID();

        Optional<UserRoot> userRoot = userRepository.findByUserId(userID);
        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userID);
        }

        UserRoot userUpdate = UserCommandMapper.customerUpdateCommandToUserRoot(command, userRoot.get());

        UserUpdatedDomainEvent userUpdatedDomainEvent = userDomainService.userUpdated(userUpdate);
        eventPublisher.publishUserUpdatedEvent(userUpdatedDomainEvent);

        UserRoot savedUserRoot = userRepository.save(userUpdate);
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + userUpdate.getUserID());
        }
    }
}