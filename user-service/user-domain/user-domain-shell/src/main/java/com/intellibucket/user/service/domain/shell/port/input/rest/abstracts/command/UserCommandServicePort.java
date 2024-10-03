package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;

public interface UserCommandServicePort {
    void companyRegistered(CompanyCreateCommand companyCreateCommand) throws UserDomainException;

    void customerRegistered(CustomerCreateCommand customerCreateCommand) throws UserDomainException;

    void updateCustomer(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException;

    void updateCompany(CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException;

    void deleteUser(UserDeleteCommand command) throws UserDomainException;

    void userLoggedIn(UserLoginCommand userLoginCommand,UserID userID) throws UserDomainException;
//
//    void changePassword(UserChangePasswordCommand userChangePasswordCommand) throws UserDomainException;
}