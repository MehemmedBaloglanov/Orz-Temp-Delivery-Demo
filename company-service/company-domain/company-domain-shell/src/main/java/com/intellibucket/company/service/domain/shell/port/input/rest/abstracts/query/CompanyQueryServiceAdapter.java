package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;

public interface CompanyQueryServiceAdapter {
    CompanyResponse getCompanyById(Long id) throws CompanyDomainException;
}
