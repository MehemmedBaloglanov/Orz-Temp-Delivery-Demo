package com.intelliacademy.orizonroute.valueobjects.common;


import com.fasterxml.jackson.annotation.JsonIgnore;

@ValueObject
public record Version(Long value) {

    public static final Version ONE = new Version(1L);
    public static final Version START = new Version(1L);

    public static Version of(Long value) {
        if (value == null || value < 1L) return ONE;
        return new Version(value);
    }

    public static Version of(Short value){
        return Version.of(value.longValue());
    }

    public Version increment() {
        return new Version(value + 1L);
    }

    public Version decrement() {
        return new Version(value - 1L);
    }

    public Version reset() {
        return new Version(1L);
    }

    public Short toShort() {
        return value.shortValue();
    }

    @JsonIgnore
    public boolean isOne() {
        return value == 1L;
    }

    @JsonIgnore
    public boolean isNotOne() {
        return value != 1L;
    }

    public Boolean isEquals(Version version) {
        return this.value.equals(version.value);
    }

    public Boolean isNotEquals(Version version) {
        return !this.value.equals(version.value);
    }

}
