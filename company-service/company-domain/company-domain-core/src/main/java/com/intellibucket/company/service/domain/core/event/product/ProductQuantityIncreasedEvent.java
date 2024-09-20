package com.intellibucket.company.service.domain.core.event.product;

import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.time.OffsetDateTime;

public class ProductQuantityIncreasedEvent extends ProductEvent{

    private final Integer increasedAmount;

    public ProductQuantityIncreasedEvent(ProductRoot productRoot, Integer increasedAmount) {
        super(productRoot, OffsetDateTime.now());
        this.increasedAmount = increasedAmount;
    }

    public Integer getIncreasedAmount() {
        return increasedAmount;
    }
}
