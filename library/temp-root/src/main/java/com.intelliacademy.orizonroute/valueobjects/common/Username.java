package com.intelliacademy.orizonroute.valueobjects.common;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@ValueObject
public final class Username {
    private static final Random RANDOM = new Random();
    private final String value;
    public Username(String value) {
        this.value = value;
    }

    public static Username of(String value) {
        return new Username(value);
    }

    private static String generate(String[] values) {
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            builder.append("_");
            builder.append(value.toLowerCase(Locale.ROOT));
        }
        return builder
                .append("&")
                .append(Username.generateRandomNumber())
                .substring(1);
    }

    public static Username generate(String firstName, String lastName) {
        return new Username(generate(new String[]{firstName, lastName}));
    }

    public static Username generate(String companyName) {
        return new Username(generate(new String[]{companyName}));
    }

    private static Integer generateRandomNumber() {
        return RANDOM.nextInt(10000,100000);
    }

    public String value() {
        return value;
    }

    public Username appendBranch(String branch) {
        return new Username(value + "@" + branch);
    }

    public Username removeBranch() {
        return new Username(value.substring(0, value.indexOf("@")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Username username)) return false;
        return Objects.equals(value(), username.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value());
    }
}
