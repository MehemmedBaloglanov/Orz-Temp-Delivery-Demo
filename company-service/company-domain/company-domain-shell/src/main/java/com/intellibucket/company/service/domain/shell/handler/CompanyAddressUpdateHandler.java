package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.company.CompanyAddressUpdateEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.company.CompanyAddressUpdateCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyAddressUpdateHandler {

    private final CompanyDomainService companyDomainService;
    private final CompanyRepositoryAdapter companyRepositoryAdapter;

    public void  handle(CompanyAddressUpdateCommand command) throws CompanyDomainException {
        CompanyID companyID = CompanyID.of(command.getCompanyId());
        CompanyRoot companyRoot = companyRepositoryAdapter.findById(companyID)
                .orElseThrow(()-> new CompanyDomainException("Company not found with id " + companyID));
        CompanyAddressUpdateEvent companyActivatedEvent = companyDomainService.companyAddressUpdate(companyRoot,companyRoot.getAddress());
        companyRepositoryAdapter.save(companyRoot);
    }
}
