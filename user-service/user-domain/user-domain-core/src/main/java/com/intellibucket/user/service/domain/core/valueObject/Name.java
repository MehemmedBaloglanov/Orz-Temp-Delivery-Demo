package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ValueObject
public final class Name {
    private final String firstName;
    private final String lastName;

    public boolean isNameValid() {
        return firstName != null && !firstName.isEmpty() &&
                lastName != null && !lastName.isEmpty();
    }

    public String getNameSurname() {
        return firstName + " " + lastName;
    }
}
