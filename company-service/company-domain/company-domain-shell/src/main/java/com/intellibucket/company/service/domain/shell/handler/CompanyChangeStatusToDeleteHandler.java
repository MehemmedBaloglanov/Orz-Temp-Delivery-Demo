package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.company.CompanyDeletedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CompanyChangeStatusToDeleteHandler {

    private final CompanyDomainService companyDomainService;
    private final CompanyRepositoryAdapter companyRepositoryAdapter;

    public void handle(CompanyDeleteCommand command) throws CompanyDomainException {
        CompanyID companyID =  CompanyID.of(command.getCompanyId());
        Optional<CompanyRoot> companyRoot = companyRepositoryAdapter.findById(companyID);

        if (companyRoot.isEmpty()) {
            throw new CompanyDomainException("Company does not found with id: " + companyID);
        }

        CompanyDeletedEvent companyDeletedEvent =  companyDomainService.deleteCompany(companyRoot.get());
        companyRepositoryAdapter.save(companyRoot.get());
    }
}
