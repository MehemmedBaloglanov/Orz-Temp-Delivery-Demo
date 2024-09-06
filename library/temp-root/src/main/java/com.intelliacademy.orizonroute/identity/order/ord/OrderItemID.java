package com.intelliacademy.orizonroute.identity.order.ord;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class OrderItemID extends BaseID<UUID> {
    private OrderItemID(UUID id) {
        super(id);
    }

    public static OrderItemID of(UUID id) {
        return new OrderItemID(id);
    }

    public static OrderItemID of(String id) {
        return new OrderItemID(UUID.fromString(id));
    }

    public static OrderItemID random() {
        return new OrderItemID(UUIDv7.randomUUID());
    }
}
