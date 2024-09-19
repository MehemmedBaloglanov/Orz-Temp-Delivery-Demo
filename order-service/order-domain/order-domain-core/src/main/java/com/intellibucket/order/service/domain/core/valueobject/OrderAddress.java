package com.intellibucket.order.service.domain.core.valueobject;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderAddress {
    private final String street;
    private final String city;
    private final String state;

    public boolean isAddressValid() {

        return state != null && !state.isEmpty()
                && street != null && !street.isEmpty()
                && city != null && !city.isEmpty();
    }
}
