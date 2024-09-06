package com.intelliacademy.orizonroute.identity;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class FileID extends BaseID<UUID> {
    protected FileID(UUID id) {
        super(id);
    }

    public static FileID of(UUID id) {
        return new FileID(id);
    }

    public static FileID of(String id) {
        return new FileID(UUID.fromString(id));
    }

    public static FileID random() {
        return new FileID(UUIDv7.randomUUID());
    }

    public static FileID nil() {
        return new FileID(null);
    }
}
