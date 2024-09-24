package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.company.CompanySuspendedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyChangeStatusToSuspendedHandler {
    private final CompanyRepositoryAdapter companyRepositoryAdapter;
    private final CompanyDomainService companyDomainService;

    public void handle(CompanyDeleteCommand command) throws CompanyDomainException {
        CompanyID companyID=CompanyID.of(command.getCompanyId());
        CompanyRoot companyRoot=companyRepositoryAdapter.findById(companyID).orElseThrow(()->
                new CompanyDomainException("Company not found with id " + companyID));
        CompanySuspendedEvent companySuspendedEvent=companyDomainService.suspendCompany(companyRoot);
        companyRepositoryAdapter.save(companyRoot);

    }
}
