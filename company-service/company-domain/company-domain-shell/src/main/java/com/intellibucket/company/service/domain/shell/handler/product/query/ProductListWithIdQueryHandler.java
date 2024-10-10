package com.intellibucket.company.service.domain.shell.handler.product.query;

import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductListWithIdQuery;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ProductListWithIdQueryHandler {
    private final ProductRepositoryAdapter productRepositoryAdapter;
    private final ProductShellDataMapper productDataMapper;


    //todo yazilmalidir
    public List<ProductResponseForOrder> handle(ProductListWithIdQuery productListWithIdQuery) {
//        List<ProductID> productIDList = productListWithIdQuery.getProductIDList();
//        return productIDList.stream()
//                .map(id -> {
//                    Optional<ProductRoot> productRootOptional = productRepositoryAdapter.findById(id);
//                    ProductResponseForOrder.ProductResponseForOrderBuilder productResponseBuilder = ProductResponseForOrder.builder();
//                    productResponseBuilder.productID(id);
//
//                    if (productRootOptional.isEmpty()) {
//                        productResponseBuilder.status(ProductConnectorStatus.NOT_FOUND);
//                        return productResponseBuilder.build();
//                    }
//
//                    ProductRoot productRoot = productRootOptional.get();
//                    if (productRoot.isActive() && productRoot.getStockQuantity() > 0) {
//
//                        ProductRoot productRoot = productRootOptional.get();
//                        productResponseBuilder.price(productRoot.getPrice());
//                        productResponseBuilder.stockQuantity(productRoot.getStockQuantity());
//                        productResponseBuilder.status(ProductConnectorStatus.ACTIVE);
//                    }
//                    return productResponseBuilder.build();
//                }).toList();
        return null;
    }
}
