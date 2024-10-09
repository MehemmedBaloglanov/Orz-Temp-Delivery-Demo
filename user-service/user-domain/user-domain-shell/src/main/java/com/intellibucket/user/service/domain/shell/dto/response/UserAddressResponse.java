package com.intellibucket.user.service.domain.shell.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAddressResponse {
    String state;
    String country;
    String city;
    String street;
    String postalCode;
}