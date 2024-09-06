package com.intelliacademy.orizonroute.identity.order.ord;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class OrderRouteRelationID extends BaseID<UUID> {
    private OrderRouteRelationID(UUID id) {
        super(id);
    }

    public static OrderRouteRelationID of(UUID id) {
        return new OrderRouteRelationID(id);
    }

    public static OrderRouteRelationID of(String id) {
        return new OrderRouteRelationID(UUID.fromString(id));
    }

    public static OrderRouteRelationID random() {
        return new OrderRouteRelationID(UUIDv7.randomUUID());
    }
}
