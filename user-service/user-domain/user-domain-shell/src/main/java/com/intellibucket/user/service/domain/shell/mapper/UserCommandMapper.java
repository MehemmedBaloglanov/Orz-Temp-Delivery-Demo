package com.intellibucket.user.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
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
    public static UserRoot companyCreateCommandToUserRoot(CompanyCreateCommand command) {
        return UserRoot.builder()
                .companyName(command.getCompanyName())
                .userID(UserID.random())
                .username(Username.generate(command.getCompanyName()))
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .password(Password.of(command.getPassword()).validate())
                .roleAuthorithy(RoleAuthorithy.COMPANY)
                .phoneNumber(PhoneNumber.of(command.getPhoneNumberType(), command.getCountryCode(), command.getPhoneNumber()).validate())
                .address(Address.of(command.getState(), command.getCountry(), command.getCity(), command.getStreet(), command.getPostalCode()).validate())
                .status(Status.ACTIVE)
                .build();
    }

    public static UserRoot customerCreateCommandToUserRoot(CustomerCreateCommand command) {
        return UserRoot.builder()
                .userID(UserID.random())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .username(Username.generate(command.getFirstName(), command.getLastName()))
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .password(Password.of(command.getPassword()).validate())
                .roleAuthorithy(RoleAuthorithy.CUSTOMER)
                .phoneNumber(PhoneNumber.of(command.getPhoneNumberType(), command.getCountryCode(), command.getPhoneNumber()).validate())
                .address(Address.of(command.getState(), command.getCountry(), command.getCity(), command.getStreet(), command.getPostalCode()).validate())
                .status(Status.ACTIVE)
                .build();
    }

    public static UserRoot userLoginCommandToUserRoot(UserLoginCommand command) {
        return UserRoot.builder()
                .email(Email.of(command.getEmailType(), command.getEmail()))
                .password(Password.of(command.getPassword()))
                .build();
    }

    public static UserRoot customerUpdateCommandToUserRoot(CustomerUpdateCommand command, UserRoot userRoot) {
        userRoot.setFirstName(command.getFirstName());
        userRoot.setLastName(command.getLastName());
        return userRoot;
    }

    public static UserRoot companyUpdateCommandToUserRoot(CompanyUpdateCommand command, UserRoot userRoot) {
        userRoot.setCompanyName(command.getCompanyName());
        return userRoot;
    }

    public static UserRoot userChangePasswordCommandToUserRoot(UserChangePasswordCommand command) {
        return UserRoot.builder()
                .password(Password.of(command.getNewPassword()))
                .build();
    }
}