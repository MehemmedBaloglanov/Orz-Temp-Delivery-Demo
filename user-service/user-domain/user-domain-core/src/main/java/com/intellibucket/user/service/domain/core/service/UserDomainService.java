package com.intellibucket.user.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.util.List;

public interface UserDomainService {

    UserRegisteredEvent companyRegistered(UserRoot userRoot);

    UserRegisteredEvent customerRegistered();

    UserDeletedDomainEvent userDeleted();

    UserChangePasswordDomainEvent userChangePassword();

    UserLoggedInDomainEvent userLoggedIn();

    UserUpdatedDomainEvent userUpdated();

    List<UserRoot> findByUserId(UserID userID);
}