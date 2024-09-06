package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class DetailID extends BaseID<UUID> {
    private DetailID(UUID id) {
        super(id);
    }

    public static DetailID of(UUID id) {
        return new DetailID(id);
    }

    public static DetailID of(String id) {
        return new DetailID(UUID.fromString(id));
    }

    public static DetailID random() {
        return new DetailID(UUIDv7.randomUUID());
    }
}
