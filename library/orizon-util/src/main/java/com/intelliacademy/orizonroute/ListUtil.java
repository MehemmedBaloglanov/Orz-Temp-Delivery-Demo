package com.intelliacademy.orizonroute;

import java.util.Collection;
import java.util.List;

public class ListUtil {
    public static <T> Boolean isNullOrEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? List.of() : list;
    }

    public static <T> Boolean isNotEmpty(Collection<T> list) {
        return !isNullOrEmpty(list);
    }

    public static <T> List<T> emptyIfNull(List<T> list, List<T> defaultValue) {
        return list == null ? defaultValue : list;
    }

    public static <T> List<T> requireNonNull(List<T> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        return list;
    }

    public static <T> List<T> requireNonNull(List<T> list, String message) {
        if (list == null) {
            throw new NullPointerException(message);
        }
        return list;
    }

    public static <T> List<T> requireNonEmpty(List<T> list) {
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public static <T> List<T> requireNonEmpty(List<T> list, String message) {
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException(message);
        }
        return list;
    }

}
