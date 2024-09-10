package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Getter;

@ValueObject
@Getter
public class CompanyAddress {
    private String city;
    private String street;
    private String address;

    public Boolean isValid() {

        return city != null
                && street != null
                && address != null
                && !city.isEmpty()
                && !street.isEmpty()
                && !address.isEmpty();

    }
}
