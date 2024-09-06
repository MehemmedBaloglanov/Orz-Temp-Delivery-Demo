package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class LicenseID extends BaseID<UUID> {
    private LicenseID(UUID id) {
        super(id);
    }

    public static LicenseID of(UUID id) {
        return new LicenseID(id);
    }

    public static LicenseID of(String id) {
        return new LicenseID(UUID.fromString(id));
    }

    public static LicenseID random() {
        return new LicenseID(UUIDv7.randomUUID());
    }
}
