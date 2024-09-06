package com.intelliacademy.orizonroute.valueobjects.common;

import java.math.BigDecimal;

@ValueObject
public final class Length {
    private final BigDecimal value;

    private Length(BigDecimal value) {
        this.value = value;
    }

    public static Length of(BigDecimal value, Unit unit) {
        return new Length(unit.toCm(value));
    }

    public static Length of(BigDecimal value) {
        return Length.of(value, Unit.CM);
    }

    public static Length ofCm(BigDecimal value) {
        return new Length(value);
    }

    public Length add(Length length) {
        return new Length(value.add(length.value));
    }

    public Length subtract(Length length) {
        return new Length(value.subtract(length.value));
    }

    public Length multiply(BigDecimal value) {
        return new Length(this.value.multiply(value));
    }

    public BigDecimal cmValue() {
        return value;
    }

    public enum Unit {
        METER,
        CM,
        INCH;

        public BigDecimal toCm(BigDecimal value) {
            return switch (this) {
                case METER: yield  value.multiply(BigDecimal.valueOf(100));
                case CM: yield value;
                case INCH: yield value.multiply(BigDecimal.valueOf(2.54));
            };
        }
    }
}
