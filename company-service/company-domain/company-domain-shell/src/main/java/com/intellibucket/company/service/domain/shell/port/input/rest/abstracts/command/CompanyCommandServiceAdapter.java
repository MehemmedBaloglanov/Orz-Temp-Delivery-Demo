package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;

public interface CompanyCommandServiceAdapter {

    CompanyResponse createCompany() throws CompanyDomainException;

    void deleteCompany(CompanyDeleteCommand command) throws CompanyDomainException;

    CompanyResponse updateCompany(Long id) throws CompanyDomainException;

    void changeStatusToSuspend(CompanyDeleteCommand command) throws CompanyDomainException;
}
