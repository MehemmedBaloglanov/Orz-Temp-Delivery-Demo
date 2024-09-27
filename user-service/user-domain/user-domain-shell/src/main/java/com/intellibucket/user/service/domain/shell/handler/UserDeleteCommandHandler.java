package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserDeletedDomainEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.adapter.UserDomainServiceImpl;
import com.intellibucket.user.service.domain.shell.dto.request.UserDeleteCommand;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDeleteCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainServiceImpl userDomainService;
private final AbstractSecurityContextHolder securityContextHolder;
    public void handle(UserDeleteCommand command) throws UserDomainException {
        UserID userID = securityContextHolder.currentUserID();
        Optional<UserRoot> userRoot = userRepository.findByUserId(userID);

        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + userID.value());
        }

        UserDeletedDomainEvent userDeletedDomainEvent = userDomainService.userDeleted(userRoot.get());

        userRepository.save(userRoot.get());
    }
}
