package com.intelliacademy.orizonroute.identity.order.ord;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class OrderItemDetailID extends BaseID<UUID> {
    private OrderItemDetailID(UUID id) {
        super(id);
    }

    public static OrderItemDetailID of(UUID id) {
        return new OrderItemDetailID(id);
    }

    public static OrderItemDetailID of(String id) {
        return new OrderItemDetailID(UUID.fromString(id));
    }

    public static OrderItemDetailID random() {
        return new OrderItemDetailID(UUIDv7.randomUUID());
    }
}
