package com.intelliacademy.orizonroute.identity.courier;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class AreaCoverageID extends BaseID<UUID> {
    private AreaCoverageID(UUID id) {
        super(id);
    }

    public static AreaCoverageID of(UUID id) {
        return new AreaCoverageID(id);
    }

    public static AreaCoverageID of(String id) {
        return new AreaCoverageID(UUID.fromString(id));
    }

    public static AreaCoverageID random() {
        return new AreaCoverageID(UUIDv7.randomUUID());
    }
}
