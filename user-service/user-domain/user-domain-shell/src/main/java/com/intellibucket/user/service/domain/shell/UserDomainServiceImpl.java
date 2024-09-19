package com.intellibucket.user.service.domain.shell;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.adapter.UserRepositoryImpl;
import com.intellibucket.service.UserRepository;
import com.intellibucket.user.service.domain.core.event.*;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.UserDomainService;
import lombok.Builder;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
@Builder

public class UserDomainServiceImpl implements UserDomainService {

    private  final UserRepository userRepository;
    @Override
    public UserRegisteredEvent companyRegistered(UserRoot userRoot) {
        userRepository.registerAsCompany(userRoot);
        return new UserRegisteredEvent(userRoot.getEmail().getValue(), OffsetDateTime.of(DomainConstants.ZONE_ID);
    }


    @Override
    public UserRegisteredEvent customerRegistered() {
        return null;
    }

    @Override
    public UserDeletedDomainEvent userDeleted() {
        return null;
    }

    @Override
    public UserChangePasswordDomainEvent userChangePassword() {
        return null;
    }


    @Override
    public UserLoggedInDomainEvent userLoggedIn() {
        return null;

    }

    @Override
    public UserUpdatedDomainEvent userUpdated() {
        return null;
    }

    @Override
    public List<UserRoot> findByUserId(UserID userID) {
        return List.of();
    }
}