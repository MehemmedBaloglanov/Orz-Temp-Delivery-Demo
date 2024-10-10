package com.intellibucket.user.service.connector.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAddressResponse {
    private String street;
    private String city;
    private String state;
}
