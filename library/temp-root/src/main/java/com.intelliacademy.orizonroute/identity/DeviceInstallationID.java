package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class DeviceInstallationID extends BaseID<UUID> {
    private DeviceInstallationID(UUID id) {
        super(id);
    }

    public static DeviceInstallationID of(UUID id) {
        return new DeviceInstallationID(id);
    }

    public static DeviceInstallationID of(String id) {
        return new DeviceInstallationID(UUID.fromString(id));
    }

    public static DeviceInstallationID random() {
        return new DeviceInstallationID(UUIDv7.randomUUID());
    }
}
