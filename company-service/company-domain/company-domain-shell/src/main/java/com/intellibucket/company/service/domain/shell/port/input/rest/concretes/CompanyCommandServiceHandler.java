package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
import com.intellibucket.company.service.domain.shell.handler.CompanyCreateCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.CompanyDeleteCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.CompanyUpdateCommandHandler;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.CompanyCommandServiceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyCommandServiceHandler implements CompanyCommandServiceAdapter {

    private final CompanyCreateCommandHandler companyCreateCommandHandler;
    private final CompanyDeleteCommandHandler companyDeleteCommandHandler;
    private final CompanyUpdateCommandHandler companyUpdateCommandHandler;

    @Override
    public CompanyResponse createCompany() throws CompanyDomainException {
        return companyCreateCommandHandler.handle();
    }

    @Override
    public void deleteCompany(CompanyDeleteCommand command) throws CompanyDomainException {
        companyDeleteCommandHandler.handle(command);
    }

    @Override
    public CompanyResponse updateCompany(Long id) throws CompanyDomainException {
        return companyUpdateCommandHandler.handle();
    }

    @Override
    public void changeStatusToSuspend(CompanyDeleteCommand command) throws CompanyDomainException {
        return ;
    }
}
