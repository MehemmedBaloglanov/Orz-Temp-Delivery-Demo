package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;

public interface CompanyCommandServiceAdapter {

    CompanyResponse createCompany() throws CompanyDomainException;

    void deleteCompany(Long id) throws CompanyDomainException;

    CompanyResponse updateCompany(Long id) throws CompanyDomainException;
}
