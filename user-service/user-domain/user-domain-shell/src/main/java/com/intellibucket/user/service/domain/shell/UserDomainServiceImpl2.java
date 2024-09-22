package com.intellibucket.user.service.domain.shell;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.UserDomainService2;
import com.intellibucket.user.service.domain.shell.handler2.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UserDomainServiceImpl2 implements UserDomainService2 {
    private CompanyRegisterServiceHandler companyRegisterServiceHandler;
    private CustomerRegisterServiceHandler customerRegisterServiceHandler;
    private LoginServiceHandler loginServiceHandler;
    private PasswordChangeServiceHandler passwordChangeServiceHandler;
    private DeleteServiceHandler deleteServiceHandler;

    @Override
    public void registerAsCompany(UserRoot userRoot, String firstName, String lastName) {
        companyRegisterServiceHandler.registerAsCompany(userRoot, firstName, lastName);
    }

    @Override
    public void registerAsCustomer(UserRoot userRoot, String firstName, String lastName) {
        customerRegisterServiceHandler.registerAsCustomer(userRoot, firstName, lastName);
    }

    @Override
    public boolean userLoggedIn(String email, String password) {
        return loginServiceHandler.userLoggedIn(email, password);
    }

    @Override
    @Transactional
    public void changePassword(UUID userId, String oldPassword, String newPassword) {
        passwordChangeServiceHandler.changePassword(userId, oldPassword, newPassword);
    }

    @Override
    public void markUserAsInactive(String userId) {
        deleteServiceHandler.markUserAsInactive(userId);
    }
}