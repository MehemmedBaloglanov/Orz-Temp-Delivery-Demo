package com.intelliacademy.orizonroute.identity.company;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class FranchiseID extends ProfileID {
    private FranchiseID(UUID id) {
        super(id);
    }

    public static FranchiseID of(UUID id) {
        return new FranchiseID(id);
    }

    public static FranchiseID of(String id) {
        return new FranchiseID(UUID.fromString(id));
    }

    public static FranchiseID random() {
        return new FranchiseID(UUIDv7.randomUUID());
    }
}
