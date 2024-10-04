package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductListWithIdQuery;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ProductListWithIdQueryHandler {
    private final ProductRepositoryAdapter productRepositoryAdapter;
    private final ProductShellDataMapper productDataMapper;


    public List<ProductResponseForOrder> handle(ProductListWithIdQuery productListWithIdQuery) {
        List<ProductID> productIDList = productListWithIdQuery.getProductIDList();
        return productIDList.stream()
                .map(id -> {
                    Optional<ProductRoot> productRootOptional = productRepositoryAdapter.findById(id);
                    ProductResponseForOrder.ProductResponseForOrderBuilder productResponseBuilder = ProductResponseForOrder.builder();
                    productResponseBuilder.productID(id);

                    if (productRootOptional.isEmpty()) {
                        productResponseBuilder.status(ProductStatus.INACTIVE);
                    } else {
                        ProductRoot productRoot = productRootOptional.get();
                        productResponseBuilder.price(productRoot.getPrice());
                        productResponseBuilder.stockQuantity(productRoot.getStockQuantity());
                        productResponseBuilder.status(ProductStatus.ACTIVE);
                    }
                    return productResponseBuilder.build();
                }).toList();
    }
}
