package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ValueObject
public final class Address {
    private final String state;
    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;

    public Address(String state, String country, String city, String street, String postalCode) {
        this.state = state;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public static Address of(String state, String country, String city, String street, String postalCode) {
        return new Address(state, country, city, street, postalCode);
    }

    public boolean isAddressValid() {
        return (street != null && !street.isEmpty() &&
                city != null && !city.isEmpty() &&
                postalCode != null && !postalCode.isEmpty() &&
                country != null && !country.isEmpty() &&
                state != null && !state.isEmpty()
        );
    }

}
