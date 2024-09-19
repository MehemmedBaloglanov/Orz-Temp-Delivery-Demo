package com.intellibucket.company.service.domain.core.event.product;

import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.time.OffsetDateTime;

public class ProductQuantityUpdatedEvent extends ProductEvent{

    public ProductQuantityUpdatedEvent(ProductRoot productRoot, OffsetDateTime createTime) {
        super(productRoot, createTime);
    }
}
