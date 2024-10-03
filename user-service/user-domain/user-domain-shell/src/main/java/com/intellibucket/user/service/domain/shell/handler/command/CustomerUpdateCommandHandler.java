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
import com.intellibucket.user.service.domain.shell.security.AbstractSecurityContextHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerUpdateCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    @Transactional
    public void handle(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        UserID customerUserID = securityContextHolder.currentCustomerID();


        Optional<UserRoot> userRoot = userRepository.findByCustomerId(customerUserID);
        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + customerUserID);
        }

        UserRoot userUpdate = UserCommandMapper.customerUpdateCommandToUserRoot(command, userRoot.get());


        UserUpdatedDomainEvent userUpdatedDomainEvent = userDomainService.userUpdated(userUpdate);
        UserRoot savedUserRoot = userRepository.save(userUpdate);
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + userUpdate.getUserID());
        }
    }
}
