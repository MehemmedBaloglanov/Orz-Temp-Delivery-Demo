package com.intellibucket.order.service.domain.shell.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddressResponse {
    private final String street;
    private final String streetAddress;
    private final String city;
}
