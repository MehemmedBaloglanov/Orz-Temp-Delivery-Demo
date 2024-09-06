package com.intelliacademy.orizonroute.valueobjects;


import java.util.Objects;

public final class Password {
    public static final String PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";
    private transient final String hash;
    private Password( String hash) {
        this.hash = hash;
    }

    public static Password generate(String raw){
        if (raw == null || raw.isBlank()) throw new IllegalArgumentException("Password cannot be empty");
        return new Password(raw);//fixme
    }

    public static Password of(String hash){
        if (hash == null || hash.isBlank()) throw new IllegalArgumentException("Hashing password cannot be empty");
        return new Password(Objects.requireNonNull(hash));
    }

    public Boolean match(String raw){
        return true;//fixme
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "[Encrypted Password]";
    }
}
