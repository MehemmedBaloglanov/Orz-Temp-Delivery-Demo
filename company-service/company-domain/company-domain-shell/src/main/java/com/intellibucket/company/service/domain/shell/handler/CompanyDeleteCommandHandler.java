package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.company.repository.entity.CompanyJpaEntity;
import com.intellibucket.company.service.domain.core.event.company.CompanyDeletedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class CompanyDeleteCommandHandler {

    private final CompanyRepositoryAdapter companyRepositoryAdapter;
    private final CompanyDomainService companyDomainService;

    public void handle(CompanyDeleteCommand command) throws CompanyDomainException {
        CompanyID companyID = CompanyID.of(command.getCompanyId());
        Optional<CompanyJpaEntity> companyRoot = companyRepositoryAdapter.findById(companyID);

        if (companyRoot.isEmpty()) {
            throw new CompanyDomainException("Company not found with id " + companyID);
        }
        CompanyDeletedEvent companyDeletedEvent = companyDomainService.deleteCompany(companyRoot.get());
        //  FIXME Outboxa save etmek lazimdir
        companyRepositoryAdapter.save(companyRoot.get());
    }
}
