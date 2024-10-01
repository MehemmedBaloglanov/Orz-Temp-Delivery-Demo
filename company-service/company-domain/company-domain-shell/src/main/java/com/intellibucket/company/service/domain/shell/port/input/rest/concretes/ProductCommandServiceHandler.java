package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.handler.ProductCreateCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.ProductDeleteCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.ProductUpdateCommandHandler;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.ProductCommandServiceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductCommandServiceHandler implements ProductCommandServiceAdapter {

    private final ProductCreateCommandHandler productCreateCommandHandler;
    private final ProductDeleteCommandHandler productDeleteCommandHandler;
    private final ProductUpdateCommandHandler productUpdateCommandHandler;


    @Override
    public ProductResponse createProduct(ProductCreateCommand command) throws CompanyDomainException {
        return productCreateCommandHandler.handle(command);
    }

    @Override
    public void deleteProduct(ProductDeleteCommand command) throws CompanyDomainException {
        productDeleteCommandHandler.handle(command);
    }

    @Override
    public void updateProduct(ProductUpdateCommand command) throws CompanyDomainException {
        productUpdateCommandHandler.handle(command);
    }
}
