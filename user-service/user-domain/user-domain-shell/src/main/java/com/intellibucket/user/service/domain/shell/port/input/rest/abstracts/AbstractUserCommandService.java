package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.*;

import java.util.Optional;

public interface AbstractUserCommandService {
    CompanyResponse companyRegistered(CompanyCreateCommand companyCreateCommand);

    CustomerResponse customerRegistered(CustomerCreateCommand customerCreateCommand);

    void userDeleted(UserID userID) throws UserDomainException;

    UserLoginResponse userLoggedIn(UserLoginCommand userLoginCommand);

    Optional<UserRoot> findByUserId(UserID userID);

    EmptyResponse changePassword(UserChangePasswordCommand userChangePasswordCommand);

    void updateUser(UserUpdateCommand command) throws UserNotFoundException;
}
