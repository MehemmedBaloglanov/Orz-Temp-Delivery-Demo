package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.CompanyQueryServiceAdapter;

public class CompanyQueryServiceHandler implements CompanyQueryServiceAdapter {
    @Override
    public CompanyResponse getCompanyById(Long id) throws CompanyDomainException {
        return null;
    }
}
