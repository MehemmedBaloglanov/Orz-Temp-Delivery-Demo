package com.intelliacademy.orizonroute.valueobjects.common;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@ValueObject
public sealed class Money permits Money.Nil {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public static final Money ONE = new Money(BigDecimal.ONE);

    public static final Money ONE_HUNDRED = new Money(BigDecimal.valueOf(100));

    public static final Money ONE_THOUSAND = new Money(BigDecimal.valueOf(1000));


    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money validatePrice() {
        if (this.getAmount() == null) {
            throw new RuntimeException("Price cannot be null or empty.");
        }
        return new Money(this.getAmount());
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(this.amount.multiply(multiplier).setScale(4, RoundingMode.HALF_UP));
    }

    public Money divide(BigDecimal divisor) {
        return new Money(this.amount.divide(divisor, 4, RoundingMode.HALF_UP));
    }

    public static Money of(Integer amount) {
        return new Money(new BigDecimal(amount));
    }

    public Boolean isGreaterThanZero() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public Boolean isGreaterThanTo(Money money) {
        return this.amount.compareTo(money.getAmount()) > 0;
    }

    public Boolean isEqualTo(Money money) {
        return this.amount.compareTo(money.getAmount()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public static Money of(BigDecimal amount) {
        if (Objects.isNull(amount)) return Nil.of();
        return new Money(amount);
    }

    public Boolean isNil() {
        return false;
    }


    public static final class Nil extends Money {

        private Nil() {
            super(BigDecimal.ZERO);
        }

        public static Nil of() {
            return new Nil();
        }

    }
}
