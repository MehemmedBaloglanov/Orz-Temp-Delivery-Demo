package com.intellibucket.order.service.domain.shell.dto.connectors.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserAddress {
    private String city;
    private String street;
    private String state;
}
