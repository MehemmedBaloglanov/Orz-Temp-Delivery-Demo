package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;

public interface ProductCommandServiceAdapter {
    ProductResponse createProduct(ProductCreateCommand command) throws CompanyDomainException;

    void deleteProduct(ProductDeleteCommand command) throws CompanyDomainException;

    void updateProduct(ProductUpdateCommand command) throws CompanyDomainException;

    void changeStatus(ProductStatusCommand command) throws CompanyDomainException;
}
