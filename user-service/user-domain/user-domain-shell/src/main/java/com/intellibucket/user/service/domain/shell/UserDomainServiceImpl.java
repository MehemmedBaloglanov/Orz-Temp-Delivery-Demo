package com.intellibucket.user.service.domain.shell;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.UserDomainService;


public class UserDomainServiceImpl implements UserDomainService {

    @Override
    public UserRegisteredEvent companyRegistered(UserRoot userRoot, UserID userID) {

        return null;
    }

    @Override
    public UserRegisteredEvent customerRegistered(UserRoot userRoot, UserID userID) {
        return null;
    }

    @Override
    public UserDeletedDomainEvent userDeleted(UserRoot userRoot) {
        return null;
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
        return null;
    }
}
