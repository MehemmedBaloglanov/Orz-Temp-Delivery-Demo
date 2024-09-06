package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class AccountPlanID extends BaseID<UUID> {
    private AccountPlanID(UUID id) {
        super(id);
    }

    public static AccountPlanID of(UUID id) {
        return new AccountPlanID(id);
    }

    public static AccountPlanID of(String id) {
        return new AccountPlanID(UUID.fromString(id));
    }

    public static AccountPlanID random() {
        return new AccountPlanID(UUIDv7.randomUUID());
    }
}
