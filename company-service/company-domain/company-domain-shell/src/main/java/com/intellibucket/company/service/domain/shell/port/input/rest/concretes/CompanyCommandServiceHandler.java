package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.*;
import com.intellibucket.company.service.domain.shell.handler.*;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.CompanyCommandServiceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyCommandServiceHandler implements CompanyCommandServiceAdapter {

    private final CompanyChangeStatusToSuspendedHandler changeStatusToSuspendedHandler;
    private final CompanyChangeStatusToActiveHandler changeStatusToActiveHandler;
    private final CompanyChangeStatusToDeleteHandler changeStatusToDeleteHandler;
    private final CompanyAddressUpdateHandler addressUpdateHandler;
    private final CompanyNameUpdateHandler nameUpdateHandler;

    @Override
    public void changeStatusToSuspend(CompanySuspendedCommand command) throws CompanyDomainException {
        changeStatusToSuspendedHandler.handle(command);
    }

    @Override
    public void changeStatusToActive(CompanyActiveCommand command) throws CompanyDomainException {
        changeStatusToActiveHandler.handle(command);
    }

    @Override
    public void changeStatusToDelete(CompanyDeleteCommand command) throws CompanyDomainException {
        changeStatusToDeleteHandler.handle(command);
    }

    @Override
    public void updateCompanyAddress(CompanyAddressUpdateCommand command) throws CompanyDomainException {
        addressUpdateHandler.handle(command);
    }

    @Override
    public void updateCompanyName(CompanyNameUpdateCommand command) throws CompanyDomainException {
        nameUpdateHandler.handle(command);
    }

}
