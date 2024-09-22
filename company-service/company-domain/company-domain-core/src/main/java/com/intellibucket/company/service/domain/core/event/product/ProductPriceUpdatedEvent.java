package com.intellibucket.company.service.domain.core.event.product;

import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.time.OffsetDateTime;

public class ProductPriceUpdatedEvent extends ProductEvent{

    public ProductPriceUpdatedEvent(ProductRoot productRoot, OffsetDateTime createTime) {
        super(productRoot, createTime);
    }
}
