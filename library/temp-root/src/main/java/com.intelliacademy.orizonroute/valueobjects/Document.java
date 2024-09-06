package com.intelliacademy.orizonroute.valueobjects;

public enum Document {
    LICENSE,
    PHOTO,
    PID,//Personal Identification Document
    PASSPORT,
    NONE;

    public String resolvePath(){

        return switch (this) {
            case LICENSE -> "license";
            case PHOTO -> "photo";
            case PID -> "pid";
            case PASSPORT -> "passport";
            case NONE -> "none";
            default -> "none";
        };
    }
}
