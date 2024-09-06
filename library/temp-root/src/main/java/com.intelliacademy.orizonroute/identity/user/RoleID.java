package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class RoleID extends BaseID<UUID> {
    private RoleID(UUID id) {
        super(id);
    }

    public static RoleID of(UUID id) {
        return new RoleID(id);
    }

    public static RoleID of(String id) {
        return new RoleID(UUID.fromString(id));
    }

    public static RoleID random() {
        return new RoleID(UUIDv7.randomUUID());
    }
}
