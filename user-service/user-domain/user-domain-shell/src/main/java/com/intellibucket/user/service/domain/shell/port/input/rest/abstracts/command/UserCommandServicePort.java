package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyUpdateCommand;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerUpdateCommand;

public interface UserCommandServicePort {
    void companyRegistered(CompanyCreateCommand companyCreateCommand) throws UserDomainException;

    void customerRegistered(CustomerCreateCommand customerCreateCommand) throws UserDomainException;

    void updateCustomer(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException;

    void updateCompany(CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException;
}
//
//    void deleteUser(UserDeleteCommand command) throws UserDomainException;
//
//   void userLoggedIn(UserLoginCommand userLoginCommand) throws UserDomainException;
//
//    void changePassword(UserChangePasswordCommand userChangePasswordCommand) throws UserDomainException;