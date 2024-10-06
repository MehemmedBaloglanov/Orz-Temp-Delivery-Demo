package com.intellibucket.order.service.domain.core.valueobject;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public final class OrderAddress {
    private final String street;
    private final String city;
    private final String state;

    public boolean isAddressValid() {

        return state != null && !state.isEmpty()
                && street != null && !street.isEmpty()
                && city != null && !city.isEmpty();
    }
}
