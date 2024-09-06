package com.intelliacademy.orizonroute.identity.company;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CommentID extends ProfileID {
    private CommentID(UUID id) {
        super(id);
    }

    public static CommentID of(UUID id) {
        return new CommentID(id);
    }

    public static CommentID of(String id) {
        return new CommentID(UUID.fromString(id));
    }

    public static CommentID random() {
        return new CommentID(UUIDv7.randomUUID());
    }
}
