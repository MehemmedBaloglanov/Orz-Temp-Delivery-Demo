package com.intelliacademy.orizonroute.identity.playground;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class PlaygroundID extends ProfileID {
    private PlaygroundID(UUID id) {
        super(id);
    }

    public static PlaygroundID of(UUID id) {
        return new PlaygroundID(id);
    }

    public static PlaygroundID of(String id) {
        return new PlaygroundID(UUID.fromString(id));
    }

    public static PlaygroundID random() {
        return new PlaygroundID(UUIDv7.randomUUID());
    }
}
