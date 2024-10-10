package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.*;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.handler.product.command.*;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.ProductCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCommandServiceHandler implements ProductCommandServiceAdapter {

    private final ProductCreateCommandHandler productCreateCommandHandler;
    private final ProductDeleteCommandHandler productDeleteCommandHandler;
    private final ProductUpdateCommandHandler productUpdateCommandHandler;
    private final ProductStatusCommandToActivateHandler productStatusCommandToActivateHandler;
    private final ProductStatusCommandToOutOfStockHandler productStatusCommandToOutOfStockHandler;


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
    public void changeStatusToActivate(ProductStatusCommand command) throws CompanyDomainException {
        productStatusCommandToActivateHandler.handle(command);
    }

    @Override
    public void changeStatusToOutOfStock(ProductStatusOutOfStockCommand command) throws CompanyDomainException {
        productStatusCommandToOutOfStockHandler.handle(command);
    }

}
