package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ValueObject
public final class Password {
    public static final String PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";
    private final String value;

    public boolean isPasswordValid() {
        return value != null &&
                !value.isEmpty() &&
                containsUpperCase(value) &&
                containsLowerCase(value) &&
                containsDigit(value) &&
                containsSpecialCharacter(value) &&
                value.length() >= 8;
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

}
