package com.intelliacademy.orizonroute.identity.courier;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class AvailableActivityHistoryID extends BaseID<UUID> {
    private AvailableActivityHistoryID(UUID id) {
        super(id);
    }

    public static AvailableActivityHistoryID of(UUID id) {
        return new AvailableActivityHistoryID(id);
    }

    public static AvailableActivityHistoryID of(String id) {
        return new AvailableActivityHistoryID(UUID.fromString(id));
    }

    public static AvailableActivityHistoryID random() {
        return new AvailableActivityHistoryID(UUIDv7.randomUUID());
    }
}
