package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;

import java.io.IOException;
import java.util.UUID;

public interface ProductQueryServiceAdapter {
    public ProductResponse getProductById(UUID id) throws CompanyDomainException;
}
