package com.intellibucket.company.service.domain.core.event.product;

import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.time.OffsetDateTime;

public class ProductPriceUpdatedEvent extends ProductEvent{
    private final Money oldPrice;
    private final Money newPrice;

    public ProductPriceUpdatedEvent(ProductRoot productRoot, Money oldPrice, Money newPrice) {
        super(productRoot, OffsetDateTime.now());
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public Money getOldPrice() {
        return oldPrice;
    }

    public Money getNewPrice() {
        return newPrice;
    }
}
