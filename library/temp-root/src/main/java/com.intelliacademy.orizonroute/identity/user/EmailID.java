package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class EmailID extends BaseID<UUID> {
    private EmailID(UUID id) {
        super(id);
    }

    public static EmailID of(UUID id) {
        return new EmailID(id);
    }

    public static EmailID of(String id) {
        return new EmailID(UUID.fromString(id));
    }

    public static EmailID random() {
        return new EmailID(UUIDv7.randomUUID());
    }
}
