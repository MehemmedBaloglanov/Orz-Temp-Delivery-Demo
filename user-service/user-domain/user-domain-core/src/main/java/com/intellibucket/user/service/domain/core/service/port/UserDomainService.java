package com.intellibucket.user.service.domain.core.service.port;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.util.List;

public interface UserDomainService {

    UserRegisteredEvent companyRegistered(UserRoot userRoot) throws UserDomainException;

    UserRegisteredEvent customerRegistered(UserRoot userRoot);

    UserDeletedDomainEvent userDeleted(UserRoot userRoot) throws UserDomainException;

    UserChangePasswordDomainEvent userChangePassword(UserRoot userRoot) throws UserDomainException;

    UserLoggedInDomainEvent userLoggedIn(UserRoot userRoot) throws UserDomainException;

    UserUpdatedDomainEvent userUpdated(UserRoot userRoot);

    List<UserRoot> findByUserId(UserID userID);
}