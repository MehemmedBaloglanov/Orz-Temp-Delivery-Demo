package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;

public interface ProductCommandServiceAdapter {
    ProductResponse createProduct() throws CompanyDomainException;

    void deleteProduct(Long id) throws CompanyDomainException;

    ProductResponse updateProduct(Long id) throws CompanyDomainException;

}
