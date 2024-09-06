package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class ContactID extends BaseID<UUID> {
    private ContactID(UUID id) {
        super(id);
    }

    public static ContactID of(UUID id) {
        return new ContactID(id);
    }

    public static ContactID of(String id) {
        return new ContactID(UUID.fromString(id));
    }

    public static ContactID random() {
        return new ContactID(UUIDv7.randomUUID());
    }
}
