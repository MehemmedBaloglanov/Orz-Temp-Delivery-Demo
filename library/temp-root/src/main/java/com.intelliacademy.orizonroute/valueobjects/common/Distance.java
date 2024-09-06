package com.intelliacademy.orizonroute.valueobjects.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@ValueObject
public final class Distance {
    public static final Distance ZERO = new Distance(BigDecimal.ZERO);
    public static final Distance ONE_METER = new Distance(BigDecimal.ONE);
    public static final Distance ONE_KILOMETER = new Distance(new BigDecimal("1000"));
    public static final Distance ONE_MILE = new Distance(new BigDecimal("1609.3440"));
    public static final Distance ONE_NAUTICAL_MILE = new Distance(new BigDecimal("1852"));
    public static final Distance MAX_VALUE = new Distance(new BigDecimal(Double.MAX_VALUE));

    private final BigDecimal value;

    private Distance(BigDecimal value) {
        this.value = value;
    }

    public static Distance of(BigDecimal value, Unit unit) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Distance value cannot be null or negative");
        return new Distance(unit.toMeters(value));
    }

    public static Distance of(BigDecimal value) {
        return of(value, Unit.METER);
    }

    public static Distance ofKm(BigDecimal value) {
        return of(value, Unit.KILOMETER);
    }

    public BigDecimal value(Unit unit) {
        return unit.fromMeters(value);
    }

    public BigDecimal value() {
        return value(Unit.METER);
    }

    public Boolean isPositiveOrZero() {
        return value.compareTo(BigDecimal.ZERO) >= 0;
    }


    public Boolean hasDifferenceGreaterThanOneMile(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.ONE_MILE);
    }

    public Boolean hasDifferenceGreaterThanOneNauticalMile(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.ONE_NAUTICAL_MILE);
    }

    public Boolean hasDifferenceGreaterThanOneMeter(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.ONE_METER);
    }

    public Boolean hasDifferenceGreaterThanTenMeters(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.of(BigDecimal.TEN));
    }

    public Boolean hasDifferenceGreaterThanHundredMeters(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.of(BigDecimal.valueOf(100)));
    }

    public Boolean hasDifferenceGreaterThanFiveHundredMeters(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.of(BigDecimal.valueOf(500)));
    }

    public Boolean hasDifferenceGreaterThanThousandMeters(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.ONE_KILOMETER);
    }

    public Boolean hasDifferenceGreaterThanTenKilometer(Distance other) {
        return this.hasDifferenceGreaterThan(other, Distance.ofKm(BigDecimal.TEN));
    }

    public Boolean hasDifferenceGreaterThan(Distance other, Distance difference) {
        return this.subtract(other).abs().isGreaterThan(difference);
    }

    public Boolean hasDifferenceSmallerThan(Distance other, Distance difference) {
        return this.subtract(other).abs().isLessThan(difference);
    }

    public Distance add(Distance other) {
        return new Distance(value.add(other.value));
    }

    public Distance subtract(Distance other) {
        return new Distance(value.subtract(other.value));
    }

    public Distance multiply(BigDecimal factor) {
        return new Distance(value.multiply(factor));
    }

    public Distance divide(BigDecimal factor) {
        return new Distance(value.divide(factor, 4, RoundingMode.HALF_UP));
    }

    public Boolean isGreaterThan(Distance other) {
        return value.compareTo(other.value) > 0;
    }

    public Boolean isGreaterThanOrEqual(Distance other) {
        return value.compareTo(other.value) >= 0;
    }

    public Boolean isLessThan(Distance other) {
        return value.compareTo(other.value) < 0;
    }

    public Boolean isLessThanOrEqual(Distance other) {
        return value.compareTo(other.value) <= 0;
    }

    public Boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public Boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Boolean isGreaterThanZero() {
        return this.isGreaterThan(Distance.ZERO);
    }

    public Boolean isGreaterThanOrEqualZero() {
        return this.isGreaterThanOrEqual(Distance.ZERO);
    }

    public Distance abs() {
        return new Distance(value.abs());
    }


    public Distance min(Distance other) {
        return this.isLessThan(other) ? this : other;
    }

    public Distance max(Distance other) {
        return this.isGreaterThan(other) ? this : other;
    }

    public Distance round() {
        return new Distance(value.setScale(0, RoundingMode.HALF_UP));
    }

    public Distance round(Integer scale) {
        return new Distance(value.setScale(scale, RoundingMode.HALF_UP));
    }

    public Distance ceil() {
        return new Distance(value.setScale(0, RoundingMode.CEILING));
    }

    public Distance floor() {
        return new Distance(value.setScale(0, RoundingMode.FLOOR));
    }

    public Distance sqrt() {
        return new Distance(BigDecimal.valueOf(Math.sqrt(value.doubleValue())));
    }

    public Distance cbrt() {
        return new Distance(BigDecimal.valueOf(Math.cbrt(value.doubleValue())));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distance distance)) return false;
        return Objects.equals(value, distance.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Distance{" + "value=" + value + " meters }";
    }

    public enum Unit {
        METER(new BigDecimal("1")),
        KILOMETER(new BigDecimal("1000")),
        MILE(new BigDecimal("1609.344")),
        NAUTICAL_MILE(new BigDecimal("1852"));

        private final BigDecimal meters;

        Unit(BigDecimal meters) {
            this.meters = meters;
        }

        public BigDecimal toMeters(BigDecimal value) {
            return value.multiply(meters);
        }

        public BigDecimal fromMeters(BigDecimal meters) {
            return meters.divide(meters, 2, RoundingMode.HALF_UP);
        }

    }
}
