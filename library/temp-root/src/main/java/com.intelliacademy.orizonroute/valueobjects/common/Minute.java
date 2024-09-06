package com.intelliacademy.orizonroute.valueobjects.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Minute {
    private final BigDecimal value;

    private Minute(BigDecimal value) {
        this.value = value;
    }

    public static Minute of(BigDecimal value) {
        return new Minute(Objects.requireNonNullElse(value, BigDecimal.ZERO));
    }

    public Minute add(Minute other) {
        return new Minute(value.add(other.value));
    }

    public Minute subtract(Minute other) {
        return new Minute(value.subtract(other.value));
    }


    public BigDecimal toMinute() {
        return value;
    }

    public Long toLong() {
        return value.longValue();
    }

    public BigDecimal toSecond() {
        return value.multiply(BigDecimal.valueOf(60));
    }

    public BigDecimal toHour() {
        return value.divide(BigDecimal.valueOf(60), RoundingMode.HALF_DOWN);
    }
}
