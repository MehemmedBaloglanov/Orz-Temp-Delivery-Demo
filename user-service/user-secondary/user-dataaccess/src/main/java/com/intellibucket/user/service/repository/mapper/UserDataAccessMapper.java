package com.intellibucket.user.service.repository.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import com.intellibucket.user.service.repository.model.PhoneNumberEntity;
import com.intellibucket.user.service.repository.model.UserAddressEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDataAccessMapper {
    public CustomerRegistrationEntity userRootToCustomerEntity(UserRoot userRoot) {
        return CustomerRegistrationEntity.builder()
                .userEntityId(UserID.random().value())
                .username(userRoot.getUsername().value())
                .email(userRoot.getEmail().getValue())
                .emailType(userRoot.getEmail().getType())
                .password(userRoot.getPassword().getValue())
                .status(userRoot.getStatus())
                .roleAuthority(userRoot.getRoleAuthorithy())
                .address(addressToUserJpaAddress(userRoot.getAddress()))
                .phoneNumberEntity(phoneNumberToPhoneNumberEntity(userRoot.getPhoneNumber()))
                .build();
    }

    public CompanyRegistrationEntity userRootToCompanyEntity(UserRoot userRoot) {
        return CompanyRegistrationEntity.builder()
                .userEntityId(UserID.random().value())
                .companyName(userRoot.getUsername().value())
                .email(userRoot.getEmail().getValue())
                .emailType(userRoot.getEmail().getType())
                .password(userRoot.getPassword().getValue())
                .status(userRoot.getStatus())
                .roleAuthority(userRoot.getRoleAuthorithy())
                .address(addressToUserJpaAddress(userRoot.getAddress()))
                .phoneNumberEntity(phoneNumberToPhoneNumberEntity(userRoot.getPhoneNumber()))
                .build();
    }

public UserRoot customerEntityToUserRoot(CustomerRegistrationEntity userEntity) {
        return UserRoot.builder()
                .userID(UserID.of(userEntity.getUserEntityId()))
                .address(userJpaAdresstoAddress(userEntity.getAddress()))
                .status(userEntity.getStatus())
                .roleAuthorithy(userEntity.getRoleAuthority())
                .password(Password.of(userEntity.getPassword()))
                .email(Email.of(userEntity.getEmailType(),userEntity.getEmail()))
                .phoneNumber(phoneNumberEntityToPhoneNumber(userEntity.getPhoneNumberEntity()))
                .build();
    }

public UserRoot companyEntityToUserRoot(CompanyRegistrationEntity userEntity) {
    return UserRoot.builder()
            .userID(UserID.of(userEntity.getUserEntityId()))
            .address(userJpaAdresstoAddress(userEntity.getAddress()))
            .status(userEntity.getStatus())
            .roleAuthorithy(userEntity.getRoleAuthority())
            .password(Password.of(userEntity.getPassword()))
            .email(Email.of(userEntity.getEmailType(),userEntity.getEmail()))
            .phoneNumber(phoneNumberEntityToPhoneNumber(userEntity.getPhoneNumberEntity()))
            .build();
}

    public Address userJpaAdresstoAddress(UserAddressEntity address) {
        return Address.builder()
                .state(address.getState())
                .street(address.getStreet())
                .city(address.getCity())
                .build();
    }

    public UserAddressEntity addressToUserJpaAddress(Address address) {
        return UserAddressEntity.builder()
                .state(address.getState())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }

    public PhoneNumber phoneNumberEntityToPhoneNumber(PhoneNumberEntity phoneNumber) {
    return PhoneNumber.builder()
            .number(phoneNumber.getNumber())
            .countryCode(phoneNumber.getCountryCode())
            .type(phoneNumber.getType())
            .build();
    }

    public PhoneNumberEntity phoneNumberToPhoneNumberEntity(PhoneNumber phoneNumber) {
        return PhoneNumberEntity.builder()
                .number(phoneNumber.getNumber())
                .type(phoneNumber.getType())
                .countryCode(phoneNumber.getCountryCode())
                .build();
    }
}