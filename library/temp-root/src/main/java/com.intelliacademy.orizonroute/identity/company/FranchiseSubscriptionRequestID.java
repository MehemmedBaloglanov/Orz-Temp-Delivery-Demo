package com.intelliacademy.orizonroute.identity.company;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class FranchiseSubscriptionRequestID extends ProfileID {
    private FranchiseSubscriptionRequestID(UUID id) {
        super(id);
    }

    public static FranchiseSubscriptionRequestID of(UUID id) {
        return new FranchiseSubscriptionRequestID(id);
    }

    public static FranchiseSubscriptionRequestID of(String id) {
        return new FranchiseSubscriptionRequestID(UUID.fromString(id));
    }

    public static FranchiseSubscriptionRequestID random() {
        return new FranchiseSubscriptionRequestID(UUIDv7.randomUUID());
    }
}
