package com.intellibucket.user.service.domain.core.service.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.constants.DomainConstants;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
@Builder
public class UserDomainServiceImpl implements UserDomainService {

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
    public UserChangePasswordDomainEvent userChangePassword(UserRoot userRoot) throws UserDomainException {
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
  //     userRepository.findByUserId(userID);
        return List.of();
    }
}