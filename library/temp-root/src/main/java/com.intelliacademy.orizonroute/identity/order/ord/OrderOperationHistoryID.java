package com.intelliacademy.orizonroute.identity.order.ord;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class OrderOperationHistoryID extends BaseID<UUID> {
    private OrderOperationHistoryID(UUID id) {
        super(id);
    }

    public static OrderOperationHistoryID of(UUID id) {
        return new OrderOperationHistoryID(id);
    }

    public static OrderOperationHistoryID of(String id) {
        return new OrderOperationHistoryID(UUID.fromString(id));
    }

    public static OrderOperationHistoryID random() {
        return new OrderOperationHistoryID(UUIDv7.randomUUID());
    }
}
