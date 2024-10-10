package com.intellibucket.company.service.domain.shell.handler.product.query;

import com.intellibucket.company.service.domain.core.exception.ProductNotFoundException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductListWithIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class ProductListWithIdQueryHandler {
    private final ProductRepositoryAdapter productRepositoryAdapter;
    private final ProductShellDataMapper productDataMapper;

    public List<ProductResponse> handle(){
        log.info("Handle product list with id query");
       return productRepositoryAdapter.findAll().stream()
               .map(productDataMapper::productRootToProductResponse).collect(Collectors.toList());
    }

}
