package com.intellibucket.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intellibucket.model.BaseUserEntity;
import com.intellibucket.model.PhoneNumberEntity;
import com.intellibucket.model.UserAddressEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.Getter;

@Getter
public class UserDataAccessMapper {
    public BaseUserEntity userRootToUserEntity(UserRoot userRoot) {
        return BaseUserEntity.builder()
                .userEntityId(UserID.random().value())
                .roleAuthority(userRoot.getRoleAuthorithy())
                .email(userRoot.getEmail().getValue())
                .emailType(userRoot.getEmail().getType())
                .status(userRoot.getStatus())
                .address(addressToUserJpaAddress(userRoot.getAddress()))
                .phoneNumberEntity(phoneNumberToPhoneNumberEntity(userRoot.getPhoneNumber()))
                .password(userRoot.getPassword().getValue())
                .build();
    }

    public UserRoot userEntityToUserRoot(BaseUserEntity userEntity) {
        return UserRoot.builder()
                .userID(UserID.of(userEntity.getUserEntityId()))
                .roleAuthorithy(userEntity.getRoleAuthority())
                .email(Email.of(userEntity.getEmailType(), userEntity.getEmail()))
                .status(userEntity.getStatus())
                .address(userJpaAdresstoAddress(userEntity.getAddress()))
                .phoneNumber(phoneNumberEntityToPhoneNumber(userEntity.getPhoneNumberEntity()))
                .password(Password.of(userEntity.getPassword()))
                .build();
    }

    public Address userJpaAdresstoAddress(UserAddressEntity address) {
        return Address.builder()
                .state(address.getState())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .build();
    }

    public UserAddressEntity addressToUserJpaAddress(Address address) {
        return UserAddressEntity.builder()
                .state(address.getState())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
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