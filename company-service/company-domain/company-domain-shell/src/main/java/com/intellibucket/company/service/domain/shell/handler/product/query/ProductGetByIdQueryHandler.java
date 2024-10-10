package com.intellibucket.company.service.domain.shell.handler.product.query;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.ProductNotFoundException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductGetByIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ProductGetByIdQueryHandler {
    private final ProductRepositoryAdapter productRepositoryAdapter;
    private final ProductShellDataMapper productDataMapper;

    public ProductResponse handle(ProductGetByIdQuery id) throws ProductNotFoundException {

        log.info("Handle get product by id query: {}", id);

        ProductID productID = ProductID.of(id.getProductId());

        ProductRoot productRoot=productRepositoryAdapter.findById(productID).orElseThrow(()-> new  ProductNotFoundException("Product not found"));
        return productDataMapper.productRootToProductResponse(productRoot);
    }
}
