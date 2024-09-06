package com.intelliacademy.orizonroute.identity.order.ord;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class OrderID extends BaseID<UUID> {
    private OrderID(UUID id) {
        super(id);
    }

    public static OrderID of(UUID id) {
        return new OrderID(id);
    }

    public static OrderID of(String id) {
        return new OrderID(UUID.fromString(id));
    }

    public static OrderID random() {
        return new OrderID(UUIDv7.randomUUID());
    }
}
