package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class VerificationID extends BaseID<UUID> {
    private VerificationID(UUID id) {
        super(id);
    }

    public static VerificationID of(UUID id) {
        return new VerificationID(id);
    }

    public static VerificationID of(String id) {
        return new VerificationID(UUID.fromString(id));
    }


    public static VerificationID random() {
        return new VerificationID(UUIDv7.randomUUID());
    }

    public static VerificationID nil() {
        return new VerificationID(null);
    }

}
