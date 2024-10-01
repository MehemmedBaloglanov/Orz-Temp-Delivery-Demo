package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import com.intellibucket.user.service.domain.core.event.UserUpdatedDomainEvent;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyUpdateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class CompanyUpdateCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    @Transactional
    public void handle(CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException {
        UserRoot userUpdate = UserCommandMapper.companyUpdateCommandToUserRoot(command);

        UserID userID = securityContextHolder.currentUserID();

        Optional<UserRoot> userRoot = userRepository.findByUserId(userID);

        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with ID" + userID.value());
        }
        UserUpdatedDomainEvent userUpdatedDomainEvent = userDomainService.userUpdated(userUpdate);

        UserRoot savedUserRoot = userRepository.save(userRoot.get());
        if (savedUserRoot == null) {
            throw new UserSavedException("User could not be saved: " + userUpdate.getUserID());
        }
    }
}