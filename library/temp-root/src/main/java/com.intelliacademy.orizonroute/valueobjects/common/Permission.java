package com.intelliacademy.orizonroute.valueobjects.common;

@ValueObject
public sealed class Permission permits Permission.Nil {
    private final String value;

    private final String description;
    private Permission(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Permission of(String value, String description) {
        return new Permission(value, description);
    }

    public static Permission empty() {
        return new Nil();
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isNil() {
        return false;
    }

    public static final class Nil extends Permission {
        public Nil() {
            super("", "");
        }

        public Boolean isNil() {
            return true;
        }
    }
}
