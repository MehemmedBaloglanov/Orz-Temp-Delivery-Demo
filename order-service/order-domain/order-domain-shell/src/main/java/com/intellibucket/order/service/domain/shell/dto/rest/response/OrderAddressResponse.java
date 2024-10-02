package com.intellibucket.order.service.domain.shell.dto.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddressResponse {
    private final String street;
    private final String state;
    private final String city;
}
