package com.intellibucket.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intelliacademy.orizonroute.valueobjects.user.PhoneNumberType;
import com.intellibucket.model.BaseUserEntity;
import com.intellibucket.model.PhoneNumberEntity;
import com.intellibucket.model.UserAddressEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDataAccessMapper {
    public BaseUserEntity userRootToUserEntity(UserRoot userRoot) {
        return BaseUserEntity.builder()
                .userEntityId(userRoot.getUserID().value())
                .roleAuthority(userRoot.getRoleAuthorithy())
                .emailType(EmailType.NONE)
                .status(userRoot.getStatus())
                .address(addressToUserJpaAddress(userRoot.getAddress()))
                .phoneNumberEntity(phoneNumberToPhoneNumberEntity(userRoot.getPhoneNumber()))
                .password(userRoot.getPassword().getValue())
                .build();
    }

    public UserRoot userEntityToUserRoot(BaseUserEntity userEntity) {
        return UserRoot.builder()
                .userID(UserID.of(UUID.randomUUID()))
                .address(userJpaAdresstoAddress(userEntity.getAddress()))
                .status(userEntity.getStatus())
                .password(Password.of(userEntity.getPassword()))
                .roleAuthorithy(userEntity.getRoleAuthority())
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