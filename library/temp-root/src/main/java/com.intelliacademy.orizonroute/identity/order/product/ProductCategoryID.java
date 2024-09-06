package com.intelliacademy.orizonroute.identity.order.product;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class ProductCategoryID extends BaseID<UUID> {
    private ProductCategoryID(UUID id) {
        super(id);
    }

    public static ProductCategoryID of(UUID id) {
        return new ProductCategoryID(id);
    }

    public static ProductCategoryID of(String id) {
        return new ProductCategoryID(UUID.fromString(id));
    }

    public static ProductCategoryID random() {
        return new ProductCategoryID(UUIDv7.randomUUID());
    }
}
