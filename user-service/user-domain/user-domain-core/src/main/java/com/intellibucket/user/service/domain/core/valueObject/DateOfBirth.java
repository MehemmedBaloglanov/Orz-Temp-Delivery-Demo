package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@ValueObject
public final class DateOfBirth {
    private final OffsetDateTime offsetDateTime;

    public boolean isDateOfBirthValid() {
        return offsetDateTime != null && offsetDateTime.getYear() > OffsetDateTime.now().getYear();
    }

}
