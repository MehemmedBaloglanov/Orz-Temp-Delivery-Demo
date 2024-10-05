package com.intellibucket.user.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserDeletedDomainEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.UserDeleteCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerDeleteCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    @Transactional
    public void handle(UserDeleteCommand command) throws UserDomainException {

        UserID customerID = securityContextHolder.currentCustomerID();

        Optional<UserRoot> userRoot = userRepository.findByCustomerId(customerID);

        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + customerID);
        }

        UserDeletedDomainEvent userDeletedDomainEvent = userDomainService.userDeleted(userRoot.get());

        userRepository.save(userRoot.get());
    }
}
