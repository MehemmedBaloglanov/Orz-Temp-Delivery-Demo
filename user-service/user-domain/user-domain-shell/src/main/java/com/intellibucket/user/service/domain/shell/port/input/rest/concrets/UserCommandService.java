package com.intellibucket.user.service.domain.shell.port.input.rest.concrets;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.handler.command.*;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command.UserCommandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService implements UserCommandServicePort {
    private final CompanyRegisterCommandHandler companyRegisterCommandHandler;
    private final CustomerRegisterCommandHandler customerRegisterCommandHandler;
    private final UserDeleteCommandHandler userDeleteCommandHandler;
    private final UserUpdateCommandHandler userUpdateCommandHandler;
    private final UserChangePasswordCommandHandler userChangePasswordCommandHandler;
    private final UserLoginCommandHandler userLoginCommandHandler;

    @Override
    public void companyRegistered(CompanyCreateCommand command) throws UserDomainException {
        companyRegisterCommandHandler.handle(command);
    }

    @Override
    public void customerRegistered(CustomerCreateCommand command) throws UserDomainException {
        customerRegisterCommandHandler.handle(command);
    }

    @Override
    public void userUpdate(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        userUpdateCommandHandler.handle(command);

    }


    @Override
    public void userDelete(UserDeleteCommand command) throws UserDomainException {
        userDeleteCommandHandler.handle(command);
    }



    @Override
    public void userLogin(UserLoginCommand command) throws UserDomainException {
       userLoginCommandHandler.handle(command);
    }


    @Override
    public void userChangePassword(UserChangePasswordCommand command) throws UserDomainException {
        userChangePasswordCommandHandler.handle(command);
    }
}

