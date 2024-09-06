package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class AvailableID extends BaseID<UUID> {
    private AvailableID(UUID id) {
        super(id);
    }

    public static AvailableID of(UUID id) {
        return new AvailableID(id);
    }

    public static AvailableID of(String id) {
        return new AvailableID(UUID.fromString(id));
    }

    public static AvailableID random() {
        return new AvailableID(UUIDv7.randomUUID());
    }
}
