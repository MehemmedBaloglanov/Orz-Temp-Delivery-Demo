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
    private final CompanyDeleteCommandHandler companyDeleteCommandHandler;
    private final CustomerUpdateCommandHandler customerUpdateCommandHandler;
    private final CustomerLoginCommandHandler customerLoginCommandHandler;
    private final CustomerChangePasswordCommandHandler customerChangePasswordCommandHandler;
    private final CompanyUpdateCommandHandler companyUpdateCommandHandler;
    private final CustomerDeleteCommandHandler customerDeleteCommandHandler;
    private final CompanyChangePasswordCommandHandler companyChangePasswordCommandHandler;
    private final CompanyLoginCommandHandler companyLoginCommandHandler;

    @Override
    public void companyRegistered(CompanyCreateCommand command) throws UserDomainException {
        companyRegisterCommandHandler.handle(command);
    }

    @Override
    public void customerRegistered(CustomerCreateCommand command) throws UserDomainException {
        customerRegisterCommandHandler.handle(command);
    }

    @Override
    public void updateCustomer(CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        customerUpdateCommandHandler.handle(command);

    }

    @Override
    public void updateCompany(CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException {
        companyUpdateCommandHandler.handle(command);
    }

    @Override
    public void companyDelete(UserDeleteCommand command) throws UserDomainException {
        companyDeleteCommandHandler.handle(command);
    }

    @Override
    public void customerDelete(UserDeleteCommand command) throws UserDomainException {
        customerDeleteCommandHandler.handle(command);
    }

    @Override
    public void customerLogin(UserLoginCommand command) throws UserDomainException {
      customerLoginCommandHandler.handle(command);
    }

    @Override
    public void companyLogin(UserLoginCommand command) throws UserDomainException {
       companyLoginCommandHandler.handle(command);
    }

    @Override
    public void customerChangePassword(UserChangePasswordCommand command) throws UserDomainException {
        customerChangePasswordCommandHandler.handle(command);
    }

    @Override
    public void companyChangePassword(UserChangePasswordCommand command) throws UserDomainException {
        companyChangePasswordCommandHandler.handle(command);
    }
}

