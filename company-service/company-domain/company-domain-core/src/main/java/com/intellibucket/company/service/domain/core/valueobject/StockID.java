package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class StockID extends BaseID<UUID> {

    private StockID(UUID id) {
        super(id);
    }

    public static StockID of(UUID id) {
        return new StockID(id);
    }

    public static StockID of(String id) {
        return new StockID(UUID.fromString(id));
    }

    public static StockID random() {
        return new StockID(UUIDv7.randomUUID());
    }
