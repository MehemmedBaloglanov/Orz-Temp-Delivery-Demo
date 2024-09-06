package com.intelliacademy.orizonroute.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UUIDv7 {
    public static UUID randomUUID() {
        return Generators.timeBasedEpochGenerator().generate();
    }

    public static UUID rawTimestampUUID(long timestamp) {
        return Generators.timeBasedGenerator().construct(timestamp);
    }
}
