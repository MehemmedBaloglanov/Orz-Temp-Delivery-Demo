package com.intellibucket.user.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.constants.DomainConstants;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
@Builder
public class UserDomainServiceImpl implements UserDomainService {

    private  final UserRepository userRepository;
    private final UserRoot userRoot;
    @Override
    public UserRegisteredEvent companyRegistered(UserRoot userRoot) {

        userRoot.initializeUser();

        return new UserRegisteredEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }


    @Override
    public UserRegisteredEvent customerRegistered(UserRoot userRoot) {
        userRoot.initializeUser();
        return new UserRegisteredEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }

    @Override
    public UserDeletedDomainEvent userDeleted(UserRoot userRoot) {
        userRoot.delete();
        return new UserDeletedDomainEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }


    @Override
    public UserChangePasswordDomainEvent userChangePassword(UserRoot userRoot ,Password oldPassword, Password newPassword) throws UserDomainException {
        userRoot.userChangePassword(oldPassword,newPassword);
        return new UserChangePasswordDomainEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }


    @Override
    public UserLoggedInDomainEvent userLoggedIn(UserRoot userRoot) throws UserDomainException {
        userRoot.validateUser();
        return new UserLoggedInDomainEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));

    }

    @Override
    public UserUpdatedDomainEvent userUpdated(UserRoot userRoot) {
        userRoot.update();
    return new UserUpdatedDomainEvent(userRoot, OffsetDateTime.now(DomainConstants.ZONE_ID));
    }

    @Override
    public List<UserRoot> findByUserId(UserID userID) {
//        userRepository.findByUserId(userID);
        return List.of();
    }
}