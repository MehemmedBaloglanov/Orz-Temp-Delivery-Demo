package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.UserLoginResponse;

import java.util.Optional;

public interface AbstractUserCommandService {
    void companyRegistered(CompanyCreateCommand companyCreateCommand) throws UserDomainException;

    void customerRegistered(CustomerCreateCommand customerCreateCommand) throws UserDomainException;

    void deleteUser(UserDeleteCommand command) throws UserDomainException;

    UserLoginResponse userLoggedIn(UserLoginCommand userLoginCommand);

    Optional<UserRoot> findByUserId(UserID userID);

    void changePassword(UserChangePasswordCommand userChangePasswordCommand) throws UserNotFoundException, PasswordValidationException;

    void updateUser(UserUpdateCommand command) throws UserNotFoundException;
}
