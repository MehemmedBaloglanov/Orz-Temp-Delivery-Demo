package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;

public interface UserCommandServicePort {
    void companyRegistered(CompanyCreateCommand companyCreateCommand) throws UserDomainException;

    void customerRegistered(CustomerCreateCommand customerCreateCommand) throws UserDomainException;

    void userUpdate(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException;

    void userDelete(UserDeleteCommand command) throws UserDomainException;

    void userLogin(UserLoginCommand userLoginCommand) throws UserDomainException;


    void userChangePassword(UserChangePasswordCommand command) throws UserDomainException;
}