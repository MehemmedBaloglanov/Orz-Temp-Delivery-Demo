package com.intellibucket.user.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.root.UserRoot;

public interface UserDomainService {

    UserRegisteredEvent companyRegistered(UserRoot userRoot, UserID userID);

    UserRegisteredEvent customerRegistered(UserRoot userRoot, UserID userID);

    UserDeletedDomainEvent userDeleted(UserRoot userRoot);

    UserChangePasswordDomainEvent userChangePassword(UserRoot userRoot);

    UserLoggedInDomainEvent userLoggedIn(UserRoot userRoot);

    UserUpdatedDomainEvent userUpdated(UserRoot userRoot);
}