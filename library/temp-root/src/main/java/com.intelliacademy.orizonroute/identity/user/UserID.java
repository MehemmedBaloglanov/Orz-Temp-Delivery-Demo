package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class UserID extends BaseID<UUID> {
    private UserID(UUID id) {
        super(id);
    }

    public static UserID of(UUID id) {
        return new UserID(id);
    }

    public static UserID of(String id) {
        return new UserID(UUID.fromString(id));
    }

    public static UserID random() {
        return new UserID(UUIDv7.randomUUID());
    }

    public UUID getId() {
        return super.getId();
    }
}
