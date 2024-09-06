package com.intelliacademy.orizonroute.valueobjects.promotion;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@ValueObject
public record Promotion(PromotionType type, PromotionSource source, String code) {
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public Promotion {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (source == null) {
            throw new IllegalArgumentException("source cannot be null");
        }
    }

    public static Promotion generate(PromotionType type, PromotionSource source) {
        var result = IntStream.rangeClosed(0, 16)
                .mapToObj(i -> CHARACTERS.charAt((int) (CHARACTERS.length() * Math.random())))
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .append("-")
                .append(LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .toString();
        return new Promotion(type,source,result);
    }

    public static Promotion of(PromotionType type, PromotionSource source, String code) {
        return new Promotion(type,source,code);
    }
}
