package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class AddressID extends BaseID<UUID> {
    private AddressID(UUID id) {
        super(id);
    }

    public static AddressID of(UUID id) {
        return new AddressID(id);
    }

    public static AddressID of(String id) {
        return new AddressID(UUID.fromString(id));
    }

    public static AddressID random() {
        return new AddressID(UUIDv7.randomUUID());
    }
}
