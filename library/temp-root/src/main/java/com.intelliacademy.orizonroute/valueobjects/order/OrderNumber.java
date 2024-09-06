package com.intelliacademy.orizonroute.valueobjects.order;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@ValueObject
public record OrderNumber(String value) {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public OrderNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Order number cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return value;
    }

    public static OrderNumber of(String value) {
        return new OrderNumber(value);
    }

    public static OrderNumber generate() {
        var result = IntStream.rangeClosed(0, 36)
                .mapToObj(i -> CHARACTERS.charAt((int) (CHARACTERS.length() * Math.random())))
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .append("-")
                .append(LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .toString();
        return new OrderNumber(result);
    }
}
