package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@ValueObject
public final class Password {
    public static final String PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";
    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password of(String value) {
        return new Password(value);
    }

    public boolean isPasswordValid() {
        return value != null &&
                !value.isEmpty() &&
                containsUpperCase(value) &&
                containsLowerCase(value) &&
                containsDigit(value) &&
                containsSpecialCharacter(value) &&
                value.length() >= 8;
    }

    public Password validate() {
        if (!isPasswordValid()) {
            throw new IllegalArgumentException("Password fields must be valid, not blank or null! Try again.");
        }
        return this;
    }

    private boolean containsUpperCase(String value) {
        return value.chars().anyMatch(Character::isUpperCase);
    }

    private boolean containsLowerCase(String value) {
        return value.chars().anyMatch(Character::isLowerCase);
    }

    private boolean containsDigit(String value) {
        return value.chars().anyMatch(Character::isDigit);
    }

    private boolean containsSpecialCharacter(String value) {
        return value.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isEqual(Password otherPassword) {
        return this.value.equals(otherPassword.getValue());
    }

    public static Password changePassword(String currentPassword, String oldPassword, String newPassword) {
        if (!currentPassword.equals(oldPassword)) {
            throw new IllegalArgumentException("Old password does not match!");
        }
        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password cannot be the same as the old password!");
        }
        return new Password(newPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}