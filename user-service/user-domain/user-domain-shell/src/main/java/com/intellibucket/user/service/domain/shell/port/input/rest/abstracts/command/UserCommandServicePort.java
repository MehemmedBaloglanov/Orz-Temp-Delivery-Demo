package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;

public interface UserCommandServicePort {
    void companyRegistered(CompanyCreateCommand companyCreateCommand) throws UserDomainException;

    void customerRegistered(CustomerCreateCommand customerCreateCommand) throws UserDomainException;

    void updateCustomer(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException;

    void updateCompany(CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException;

    void companyDelete(UserDeleteCommand command) throws UserDomainException;

    void customerDelete(UserDeleteCommand command) throws UserDomainException;

    void customerLogin(UserLoginCommand userLoginCommand) throws UserDomainException;

    void companyLogin(UserLoginCommand userLoginCommand) throws UserDomainException;

    void customerChangePassword(UserChangePasswordCommand command) throws UserDomainException;

    void companyChangePassword(UserChangePasswordCommand command) throws UserDomainException;
}