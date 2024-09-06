package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class PasswordID extends BaseID<UUID> {
    private PasswordID(UUID id) {
        super(id);
    }

    public static PasswordID of(UUID id) {
        return new PasswordID(id);
    }

    public static PasswordID of(String id) {
        return new PasswordID(UUID.fromString(id));
    }

    public static PasswordID random() {
        return new PasswordID(UUIDv7.randomUUID());
    }
}
