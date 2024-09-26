package com.intellibucket.user.service.domain.shell.dto.command.abstracts;

import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intelliacademy.orizonroute.valueobjects.user.PhoneNumberType;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractUserCreateCommand {
    String email;

    String password;

    RoleAuthorithy roleAuthorithy;

    EmailType emailType;

    String countryCode;

    PhoneNumberType phoneNumberType;

    String phoneNumber;
    // USER ADDRESS
    String street;
    String city;
    String state;
    String postalCode;
    String country;
}