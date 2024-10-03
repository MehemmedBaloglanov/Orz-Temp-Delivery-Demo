package com.intellibucket.user.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserUpdatedDomainEvent;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerUpdateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import publisher.KafkaEventPublisher;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerUpdateCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final KafkaEventPublisher eventPublisher;
//    private final AbstractSecurityContextHolder securityContextHolder;

    public void handle(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        UserRoot userUpdate = UserCommandMapper.customerUpdateCommandToUserRoot(command);

        UserID userID = userUpdate.getUserID();

        Optional<UserRoot> userRoot = userRepository.findByUserId(userID);

        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with ID" + userID.value());
        }
        UserUpdatedDomainEvent userUpdatedDomainEvent = userDomainService.userUpdated(userUpdate);
        eventPublisher.publishUserUpdatedEvent(userUpdatedDomainEvent);

        UserRoot savedUserRoot = userRepository.save(userUpdate);
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + userUpdate.getUserID());
        }
    }
}