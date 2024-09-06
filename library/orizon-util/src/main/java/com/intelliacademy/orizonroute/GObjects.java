package com.intelliacademy.orizonroute;

import java.util.function.Supplier;

public class GObjects {
    public static <T> T getOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    @SafeVarargs
    public static <T> T getOrDefault(T value, T defaultValue, T... otherValues) {
        if (value != null) {
            return value;
        }
        for (T otherValue : otherValues) {
            if (otherValue != null) {
                return otherValue;
            }
        }
        return defaultValue;
    }

    public static <T> T getOrDefault(T value, T defaultValue, T otherValue) {
        if (value != null) {
            return value;
        }
        return otherValue != null ? otherValue : defaultValue;
    }

    public static <T> T getOrThrow(T value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <T> T getOrThrow(T value, String message, Object... args) {
        if (value == null) {
            throw new IllegalArgumentException(String.format(message, args));
        }
        return value;
    }

    public static <T> T getOrThrow(T value, Supplier<Exception> exception) throws Exception {
        if (value == null) {
            throw exception.get();
        }
        return value;
    }


    public static String getOrThrow(String key) {
        return getOrThrow(key, "Key is required");
    }
}
