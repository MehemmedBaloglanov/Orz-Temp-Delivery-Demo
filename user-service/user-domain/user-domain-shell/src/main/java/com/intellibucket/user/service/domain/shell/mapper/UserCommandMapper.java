package com.intellibucket.user.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.request.*;

public class UserCommandMapper {
    // CompanyRegisterCommand to UserRoot mapping
    public static UserRoot companyCreateCommandToUserRoot(CompanyCreateCommand command) {
        return UserRoot.builder()
                .username(Username.generate(command.getCompanyName()))
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .password(Password.of(command.getPassword()))
                .roleAuthorithy(RoleAuthorithy.COMPANY)
                .phoneNumber(PhoneNumber.of(command.getPhoneNumberType(), command.getCountryCode(), command.getPhoneNumber()))
                .address(Address.of(command.getState(), command.getCountry(), command.getCity(), command.getStreet(), command.getPostalCode()))
                .status(Status.ACTIVE)
                .build();
    }

    // CustomerRegisterCommand to UserRoot mapping
    public static UserRoot customerCreateCommandToUserRoot(CustomerCreateCommand command) {
        return UserRoot.builder()
                .username(Username.generate(command.getFirstName(), command.getLastName()))
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .password(Password.of(command.getPassword()))
                .roleAuthorithy(RoleAuthorithy.CUSTOMER)
                .phoneNumber(PhoneNumber.of(command.getPhoneNumberType(), command.getCountryCode(), command.getPhoneNumber()))
                .address(Address.of(command.getState(), command.getCountry(), command.getCity(), command.getStreet(), command.getPostalCode()))
                .status(Status.ACTIVE)
                .build();
    }

    public static UserRoot userLoginCommandToUserRoot(UserLoginCommand command) {
        return UserRoot.builder()
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .password(Password.of(command.getPassword()))
                .build();
    }

    public static UserRoot customerUpdateCommandToUserRoot(CustomerUpdateCommand command) {
        return UserRoot.builder()
                .username(Username.generate(command.getFirstName(), command.getLastName()))
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .phoneNumber(PhoneNumber.of(command.getPhoneNumberType(), command.getCountryCode(), command.getPhoneNumber()))
                .address(Address.of(command.getState(), command.getCountry(), command.getCity(), command.getStreet(), command.getPostalCode()))
                .build();
    }

    public static UserRoot companyUpdateCommandToUserRoot(CompanyUpdateCommand command) {
        return UserRoot.builder()
                .username(Username.generate(command.getCompanyName()))
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .phoneNumber(PhoneNumber.of(command.getPhoneNumberType(), command.getCountryCode(), command.getPhoneNumber()))
                .address(Address.of(command.getState(), command.getCountry(), command.getCity(), command.getStreet(), command.getPostalCode()))
                .build();
    }
}