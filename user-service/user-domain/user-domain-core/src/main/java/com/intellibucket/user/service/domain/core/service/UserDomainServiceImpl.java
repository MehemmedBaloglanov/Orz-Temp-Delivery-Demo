package com.intellibucket.user.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.constants.DomainConstants;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
@Builder

public class UserDomainServiceImpl implements UserDomainService {

    private  final UserRepository userRepository;
    private final UserRoot userRoot;
    @Override
    public UserRegisteredEvent companyRegistered(UserRoot userRoot) {
        return null;
    }


    @Override
    public UserRegisteredEvent customerRegistered(UserRoot userRoot) {
       return null;
    }

    @Override
    public UserDeletedDomainEvent userDeleted(UserRoot userRoot) throws UserDomainException {
        userRoot.delete();
        userRepository.delete(userRoot);
        return new UserDeletedDomainEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }


    @Override
    public UserChangePasswordDomainEvent userChangePassword(UserRoot userRoot) {
        return null;
    }


    @Override
    public UserLoggedInDomainEvent userLoggedIn(UserRoot userRoot) {
        return null;

    }

    @Override
    public UserUpdatedDomainEvent userUpdated(UserRoot userRoot) {
        userRepository.update(userRoot);
    return new UserUpdatedDomainEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }

    @Override
    public List<UserRoot> findByUserId(UserID userID) {
        userRepository.findByUserId(userID);
        return List.of();
    }
}