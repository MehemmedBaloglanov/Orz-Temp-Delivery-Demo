package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.company.CompanyActivatedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.company.CompanyActiveCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CompanyChangeStatusToActiveHandler {

    private final CompanyRepositoryAdapter companyRepositoryAdapter;
    private final CompanyDomainService companyDomainService;

    public void handle(CompanyActiveCommand command) throws CompanyDomainException {
        CompanyID companyID = CompanyID.of(command.getCompanyId());
        Optional<CompanyRoot> companyRoot = companyRepositoryAdapter.findById(companyID);

        if (companyRoot.isEmpty()){
            throw new CompanyDomainException("Company not found with id " + companyID);
        }

        CompanyActivatedEvent companyActivatedEvent = companyDomainService.activateCompany(companyRoot.get());
        companyRepositoryAdapter.save(companyRoot.get());
    }
}

