package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.*;

import java.util.Optional;

public interface AbstractUserCommandService {
    void companyRegistered(CompanyCreateCommand companyCreateCommand) throws UserDomainException;

    void customerRegistered(CustomerCreateCommand customerCreateCommand) throws UserDomainException;

    void deleteUser(UserDeleteCommand command) throws UserDomainException;

   void userLoggedIn(UserLoginCommand userLoginCommand) throws UserDomainException;

    Optional<UserRoot> findByUserId(UserID userID);

    void changePassword(UserChangePasswordCommand userChangePasswordCommand) throws UserDomainException;

    void updateCustomer(CustomerUpdateCommand command ) throws UserNotFoundException, UserSavedException;

    void updateCompany(CompanyUpdateCommand command ) throws UserNotFoundException, UserSavedException;
}
