package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.company.CompanyNameUpdateEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyNameUpdateCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyNameUpdateHandler {

    private final CompanyDomainService companyDomainService;
    private final CompanyRepositoryAdapter companyRepositoryAdapter;

    public void handle(CompanyNameUpdateCommand command) throws CompanyDomainException {
        CompanyID companyID = CompanyID.of(command.getCompanyId());
        CompanyRoot companyRoot = companyRepositoryAdapter.findById(companyID)
                .orElseThrow(()-> new CompanyDomainException("Company not found with id " + companyID));
        CompanyNameUpdateEvent companyNameUpdateEvent = companyDomainService.companyNameUpdate(companyRoot,companyRoot.getCompanyName());
        companyRepositoryAdapter.save(companyRoot);
    }
}
