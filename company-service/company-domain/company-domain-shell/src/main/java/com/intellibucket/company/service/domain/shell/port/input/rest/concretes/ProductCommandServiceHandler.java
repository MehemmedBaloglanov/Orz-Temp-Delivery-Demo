package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
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
    public ProductResponse createProduct() throws CompanyDomainException {
        return productCreateCommandHandler.handle();
    }

    @Override
    public void deleteProduct(Long id) throws CompanyDomainException {
        productDeleteCommandHandler.handle();
    }

    @Override
    public ProductResponse updateProduct(Long id) {
        return productUpdateCommandHandler.handle();
    }
}
