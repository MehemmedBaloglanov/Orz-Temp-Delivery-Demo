package com.intelliacademy.orizonroute.identity.order.product;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class ProductID extends BaseID<UUID> {
    private ProductID(UUID id) {
        super(id);
    }

    public static ProductID of(UUID id) {
        return new ProductID(id);
    }

    public static ProductID of(String id) {
        return new ProductID(UUID.fromString(id));
    }

    public static ProductID random() {
        return new ProductID(UUIDv7.randomUUID());
    }
}
