package com.intellibucket.user.service.domain.shell.port.input.rest.concrets;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.*;
import com.intellibucket.user.service.domain.shell.handler.*;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandService implements AbstractUserCommandService {
    private final CompanyRegisterCommandHandler companyRegisterCommandHandler;
    private final CustomerRegisterCommandHandler customerRegisterCommandHandler;
    private final UserDeleteCommandHandler userDeleteCommandHandler;
    private final UserUpdateCommandHandler userUpdateCommandHandler;
    private final UserLoginCommandHandler userLoginCommandHandler;
    private final UserChangePasswordCommandHandler userChangePasswordCommandHandler;

    @Override
    public void companyRegistered(CompanyCreateCommand command) throws UserDomainException {
        companyRegisterCommandHandler.handle(command);
    }

    @Override
    public void customerRegistered(CustomerCreateCommand command) throws UserDomainException {
         customerRegisterCommandHandler.handle(command);
    }

    @Override
    public void deleteUser(UserDeleteCommand command) throws UserDomainException {
        userDeleteCommandHandler.handle(command);
    }

    @Override
    public UserLoginResponse userLoggedIn(UserLoginCommand userLoginCommand) {
    return null;}

    @Override
    public Optional<UserRoot> findByUserId(UserID userID) {
        return Optional.empty();
    }

    @Override
    public void changePassword(UserChangePasswordCommand command) throws UserNotFoundException, PasswordValidationException {
      userChangePasswordCommandHandler.handle(command);

    }

    @Override
    public void updateUser(UserUpdateCommand command) throws UserNotFoundException {
      userUpdateCommandHandler.handle(command);

    }


}
