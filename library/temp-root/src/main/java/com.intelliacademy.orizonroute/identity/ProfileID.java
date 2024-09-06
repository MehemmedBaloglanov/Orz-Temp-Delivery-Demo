package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class ProfileID extends BaseID<UUID> {
    protected ProfileID(UUID id) {
        super(id);
    }

    public static ProfileID of(UUID id) {
        return new ProfileID(id);
    }

    public static ProfileID of(String id) {
        return new ProfileID(UUID.fromString(id));
    }

    public static ProfileID random() {
        return new ProfileID(UUIDv7.randomUUID());
    }
}
