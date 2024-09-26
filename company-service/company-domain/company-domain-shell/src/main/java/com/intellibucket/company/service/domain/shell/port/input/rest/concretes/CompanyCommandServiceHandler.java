package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyActiveCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanySuspendedCommand;
import com.intellibucket.company.service.domain.shell.handler.CompanyChangeStatusToActiveHandler;
import com.intellibucket.company.service.domain.shell.handler.CompanyChangeStatusToSuspendedHandler;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.CompanyCommandServiceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyCommandServiceHandler implements CompanyCommandServiceAdapter {

    private final CompanyChangeStatusToSuspendedHandler changeStatusToSuspendedHandler;
    private final CompanyChangeStatusToActiveHandler changeStatusToActiveHandler;

    @Override
    public void changeStatusToSuspend(CompanySuspendedCommand command) throws CompanyDomainException {
        changeStatusToSuspendedHandler.handle(command);
    }

    @Override
    public void changeStatusToActive(CompanyActiveCommand command) throws CompanyDomainException {
        changeStatusToActiveHandler.handle(command);
    }

}
