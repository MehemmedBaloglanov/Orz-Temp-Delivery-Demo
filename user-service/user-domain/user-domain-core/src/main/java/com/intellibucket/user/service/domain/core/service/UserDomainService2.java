package com.intellibucket.user.service.domain.core.service;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;

import java.util.UUID;

public interface UserDomainService2 {
    void registerAsCompany(UserRoot userRoot, String firstName, String lastName);

    void registerAsCustomer(UserRoot userRoot, String firstName, String lastName);

    boolean userLoggedIn(String username, String password);

    void changePassword(UUID userId, String oldPassword, String newPassword);

    void markUserAsInactive(String userRoot);
}