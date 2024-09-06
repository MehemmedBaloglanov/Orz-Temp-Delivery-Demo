package com.intelliacademy.orizonroute.identity.order.ord;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class OrderCancellationID extends BaseID<UUID> {
    private OrderCancellationID(UUID id) {
        super(id);
    }

    public static OrderCancellationID of(UUID id) {
        return new OrderCancellationID(id);
    }

    public static OrderCancellationID of(String id) {
        return new OrderCancellationID(UUID.fromString(id));
    }

    public static OrderCancellationID random() {
        return new OrderCancellationID(UUIDv7.randomUUID());
    }
}
