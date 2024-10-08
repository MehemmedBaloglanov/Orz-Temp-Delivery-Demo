package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query;

import com.intellibucket.company.service.domain.core.exception.ProductNotFoundException;
import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductGetByIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductListWithIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;

import java.util.List;

public interface ProductQueryServiceAdapter {
    ProductResponse getProductById(ProductGetByIdQuery id) throws ProductNotFoundException;

    List<ProductResponseForOrder> listProduct(ProductListWithIdQuery productListWithIdQuery);
}
