package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.ProductQueryServiceAdapter;

import java.util.UUID;

public class ProductQueryServiceHandler implements ProductQueryServiceAdapter {
    @Override
    public ProductResponse getProductById(UUID id) throws CompanyDomainException {
        return null;
    }
}
