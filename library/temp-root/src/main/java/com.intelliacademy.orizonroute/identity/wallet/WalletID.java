package com.intelliacademy.orizonroute.identity.wallet;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class WalletID extends BaseID<UUID> {
    private WalletID(UUID id) {
        super(id);
    }

    public static WalletID of(UUID id) {
        return new WalletID(id);
    }

    public static WalletID of(String id) {
        return new WalletID(UUID.fromString(id));
    }

    public static WalletID random() {
        return new WalletID(UUIDv7.randomUUID());
    }
}
