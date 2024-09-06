package com.intelliacademy.orizonroute.valueobjects.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ValueObject
public final class Weight {
    private final BigDecimal value;

    private Weight(BigDecimal value) {
        this.value = value;
    }

    public static Weight of(BigDecimal value, Unit unit) {
        return new Weight(unit.toKg(value));
    }

    public static Weight ofKg(BigDecimal value) {
        return new Weight(value);
    }

    public Weight add(Weight weight) {
        return new Weight(value.add(weight.value));
    }

    public Weight subtract(Weight weight) {
        return new Weight(value.subtract(weight.value));
    }

    public Weight multiply(BigDecimal value) {
        return new Weight(this.value.multiply(value));
    }

    public BigDecimal kgValue() {
        return value;
    }

    public enum Unit {
        G,
        KG,

        T,
        POUND;

        public BigDecimal toKg(BigDecimal value) {
            return switch (this) {
                case G: yield value.divide(BigDecimal.valueOf(1000), 5, RoundingMode.HALF_UP);
                case KG: yield value;
                case T: yield value.multiply(BigDecimal.valueOf(1000));
                case POUND: yield value.multiply(BigDecimal.valueOf(0.45359237));
            };
        }
    }
}
