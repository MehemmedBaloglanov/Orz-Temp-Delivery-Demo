package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ValueObject
public final class ContactInfo {
    private final String phoneNumber;

    public boolean isContactInfoValid() {
        return phoneNumber != null &&
                !phoneNumber.isEmpty();
    }

}