package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@ValueObject
@Builder
@RequiredArgsConstructor
public final class CompanyAddress {
    private final String city;
    private final String street;
    private final String address;

    public Boolean isValid() {

        return city != null
                && street != null
                && address != null
                && !city.isEmpty()
                && !street.isEmpty()
                && !address.isEmpty();

    }

}
