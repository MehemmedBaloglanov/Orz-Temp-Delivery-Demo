package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class DeviceID extends BaseID<UUID> {
    private DeviceID(UUID id) {
        super(id);
    }

    public static DeviceID of(UUID id) {
        return new DeviceID(id);
    }

    public static DeviceID of(String id) {
        return new DeviceID(UUID.fromString(id));
    }

    public static DeviceID random() {
        return new DeviceID(UUIDv7.randomUUID());
    }
}
