package com.intelliacademy.orizonroute.identity.courier;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class PackageRequirementID extends BaseID<UUID> {
    private PackageRequirementID(UUID id) {
        super(id);
    }

    public static PackageRequirementID of(UUID id) {
        return new PackageRequirementID(id);
    }

    public static PackageRequirementID of(String id) {
        return new PackageRequirementID(UUID.fromString(id));
    }

    public static PackageRequirementID random() {
        return new PackageRequirementID(UUIDv7.randomUUID());
    }
}
