package com.intelliacademy.orizonroute.identity.route;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class RouteID extends BaseID<UUID> {
    private RouteID(UUID id) {
        super(id);
    }

    public static RouteID of(UUID id) {
        return new RouteID(id);
    }

    public static RouteID of(String id) {
        return new RouteID(UUID.fromString(id));
    }

    public static RouteID random() {
        return new RouteID(UUIDv7.randomUUID());
    }
}
