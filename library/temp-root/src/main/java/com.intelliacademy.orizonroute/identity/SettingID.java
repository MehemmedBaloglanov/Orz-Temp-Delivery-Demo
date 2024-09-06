package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class SettingID extends BaseID<UUID> {
    private SettingID(UUID id) {
        super(id);
    }

    public static SettingID of(UUID id) {
        return new SettingID(id);
    }

    public static SettingID of(String id) {
        return new SettingID(UUID.fromString(id));
    }

    public static SettingID random() {
        return new SettingID(UUIDv7.randomUUID());
    }
}
