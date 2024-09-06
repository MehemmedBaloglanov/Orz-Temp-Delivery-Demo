package com.intelliacademy.orizonroute.identity.tracking;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class LocationID extends BaseID<UUID> {
    private LocationID(UUID id) {
        super(id);
    }

    public static LocationID of(UUID id) {
        return new LocationID(id);
    }

    public static LocationID of(String id) {
        return new LocationID(UUID.fromString(id));
    }

    public static LocationID random() {
        return new LocationID(UUIDv7.randomUUID());
    }

    public static LocationID empty() {
        return new LocationID(null);
    }
}
