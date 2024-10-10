package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.ProductNotFoundException;
import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductGetByIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductListWithIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.handler.product.query.ProductGetByIdQueryHandler;
import com.intellibucket.company.service.domain.shell.handler.product.query.ProductListWithIdQueryHandler;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.ProductQueryServiceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductQueryServiceHandler implements ProductQueryServiceAdapter {
    private final ProductGetByIdQueryHandler productGetByIdQueryHandler;
    private final ProductListWithIdQueryHandler productListWithIdQueryHandler;

    @Override
    public ProductResponse getProductById(ProductGetByIdQuery productGetByIdQuery) throws ProductNotFoundException {
        return productGetByIdQueryHandler.handle(productGetByIdQuery);
    }

    @Override
    public List<ProductResponseForOrder> listProduct(ProductListWithIdQuery productListWithIdQuery) {
        return productListWithIdQueryHandler.handle(productListWithIdQuery);
    }
}
