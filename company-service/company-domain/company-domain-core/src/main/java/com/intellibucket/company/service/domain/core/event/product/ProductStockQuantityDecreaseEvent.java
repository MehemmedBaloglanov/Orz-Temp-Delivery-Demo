package com.intellibucket.company.service.domain.core.event.product;

import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.time.OffsetDateTime;

public class ProductStockQuantityDecreaseEvent extends ProductEvent {
    public ProductStockQuantityDecreaseEvent(ProductRoot productRoot, OffsetDateTime createTime) {
        super(productRoot, createTime);
    }
}
