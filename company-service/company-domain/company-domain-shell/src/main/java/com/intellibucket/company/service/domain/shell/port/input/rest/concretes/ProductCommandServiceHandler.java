package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.handler.product.command.ProductCreateCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.product.command.ProductDeleteCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.product.command.ProductStatusCommandHandler;
import com.intellibucket.company.service.domain.shell.handler.product.command.ProductUpdateCommandHandler;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.ProductCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCommandServiceHandler implements ProductCommandServiceAdapter {

    private final ProductCreateCommandHandler productCreateCommandHandler;
    private final ProductDeleteCommandHandler productDeleteCommandHandler;
    private final ProductUpdateCommandHandler productUpdateCommandHandler;
    private final ProductStatusCommandHandler productStatusCommandHandler;


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

    @Override
    public void changeStatus(ProductStatusCommand command) throws CompanyDomainException {
        productStatusCommandHandler.handle(command);
    }
}
