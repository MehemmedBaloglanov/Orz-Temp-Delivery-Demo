package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;
import lombok.Builder;
import lombok.Getter;

@ValueObject
@Getter
@Builder
public record CompanyAddress(String city, String street, String address) {

        public Boolean isValid() {

        return city != null
                && street != null
                && address != null
                && !city.isEmpty()
                && !street.isEmpty()
                && !address.isEmpty();

    }
}
