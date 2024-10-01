package com.intellibucket.user.service.domain.shell.port.input.rest.concrets;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerCreateCommand;
import com.intellibucket.user.service.domain.shell.handler.*;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command.UserCommandServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCommandService implements UserCommandServicePort {
    private final CompanyRegisterCommandHandler companyRegisterCommandHandler;
    private final CustomerRegisterCommandHandler customerRegisterCommandHandler;
    private final UserDeleteCommandHandler userDeleteCommandHandler;
    private final CustomerUpdateCommandHandler customerUpdateCommandHandler;
    private final UserLoginCommandHandler userLoginCommandHandler;
    private final UserChangePasswordCommandHandler userChangePasswordCommandHandler;
    private final CompanyUpdateCommandHandler companyUpdateCommandHandler;

    @Override
    public void companyRegistered(CompanyCreateCommand command) throws UserDomainException {
        companyRegisterCommandHandler.handle(command);
    }

    @Override
    public void customerRegistered(CustomerCreateCommand command) throws UserDomainException {
        customerRegisterCommandHandler.handle(command);
    }
//
//    @Override
//    public void deleteUser(UserDeleteCommand command) throws UserDomainException {
//        userDeleteCommandHandler.handle(command);
//    }
//
//    @Override
//    public void userLoggedIn(UserLoginCommand command) throws UserDomainException {
//        userLoginCommandHandler.handle(command);
//    }
//
//    @Override
//    public void changePassword(UserChangePasswordCommand command) throws UserDomainException {
//        userChangePasswordCommandHandler.handle(command);
//
//    }
//
//    @Override
//    public void updateCustomer(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
//        customerUpdateCommandHandler.handle(command);
//
//    }
//
//    @Override
//    public void updateCompany(CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException {
//        companyUpdateCommandHandler.handle(command);
//    }
}