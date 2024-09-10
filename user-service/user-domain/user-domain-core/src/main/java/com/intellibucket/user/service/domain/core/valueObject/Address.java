package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ValueObject
public final class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public boolean isAddressValid() {
        return (street != null && !street.isEmpty() &&
                city != null && !city.isEmpty() &&
                postalCode != null && !postalCode.isEmpty() &&
                country != null && !country.isEmpty() &&
                state != null && !state.isEmpty()
        );
    }

}
