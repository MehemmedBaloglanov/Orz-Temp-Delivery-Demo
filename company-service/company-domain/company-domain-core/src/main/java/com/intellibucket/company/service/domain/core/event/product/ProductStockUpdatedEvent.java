package com.intellibucket.company.service.domain.core.event.product;

import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.time.OffsetDateTime;

public class ProductStockUpdatedEvent extends ProductEvent{

    public ProductStockUpdatedEvent(ProductRoot productRoot, OffsetDateTime createTime) {
        super(productRoot, createTime);
    }
}
