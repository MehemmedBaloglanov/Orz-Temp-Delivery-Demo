package com.intelliacademy.orizonroute.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.util.function.Predicate;

@ValueObject
public final class Quantity {
    public static final Quantity ZERO = new Quantity(BigInteger.ZERO);

    private final BigInteger value;

    private Quantity(BigInteger value) {
        this.value = value;
    }


    public static Quantity of(BigInteger value) {
        return new Quantity(value);
    }

    public static Quantity of(String value) {
        return new Quantity(new BigInteger(value));
    }

    public static Quantity of(long value) {
        return new Quantity(BigInteger.valueOf(value));
    }

    public static Quantity of(int value) {
        return new Quantity(BigInteger.valueOf(value));
    }

    public Quantity ifPossibleDoIt(Predicate<Quantity> predicate, Quantity quantity) {
        if (predicate.test(this)) {
            return quantity;
        }
        return this;
    }

    public Quantity add(BigInteger value) {
        return new Quantity(this.value.add(value));
    }

    public Quantity subtract(BigInteger value) {
        return new Quantity(this.value.subtract(value));
    }

    public Quantity add(Quantity quantity) {
        return new Quantity(value.add(quantity.value));
    }

    public Quantity subtract(Quantity quantity) {
        return new Quantity(value.subtract(quantity.value));
    }

    public Quantity multiply(BigInteger value) {
        return new Quantity(this.value.multiply(value));
    }

    public Quantity divide(BigInteger value) {
        return new Quantity(this.value.divide(value));
    }

    public Quantity divide(int value) {
        return new Quantity(this.value.divide(BigInteger.valueOf(value)));
    }

    public Quantity divide(Quantity quantity) {
        return new Quantity(this.value.divide(quantity.value));
    }

    public Quantity multiply(int value) {
        return new Quantity(this.value.multiply(BigInteger.valueOf(value)));
    }

    public Quantity multiply(Quantity quantity) {
        return new Quantity(this.value.multiply(quantity.value));
    }

    public boolean isGreaterThan(Quantity quantity) {
        return value.compareTo(quantity.value) > 0;
    }

    public boolean isLessThan(Quantity quantity) {
        return value.compareTo(quantity.value) < 0;
    }

    public boolean isGreaterThanOrEqualTo(Quantity quantity) {
        return value.compareTo(quantity.value) >= 0;
    }

    public boolean isLessThanOrEqualTo(Quantity quantity) {
        return value.compareTo(quantity.value) <= 0;
    }

    public boolean isEqualTo(Quantity quantity) {
        return value.compareTo(quantity.value) == 0;
    }

    public Integer intValue() {
        return value.intValue();
    }

    @JsonIgnore
    public BigInteger value() {
        return value;
    }
}
