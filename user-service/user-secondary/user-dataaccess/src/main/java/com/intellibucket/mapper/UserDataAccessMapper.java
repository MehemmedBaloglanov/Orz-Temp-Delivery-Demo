package com.intellibucket.mapper;

import com.intelliacademy.orizonroute.identity.AddressID;
import com.intelliacademy.orizonroute.identity.user.PhoneNumberID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intellibucket.model.CompanyRegistrationEntity;
import com.intellibucket.model.PhoneNumberEntity;
import com.intellibucket.model.UserAddressEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.Getter;

@Getter
public class UserDataAccessMapper {
    public CompanyRegistrationEntity userRootToCompanyEntity(UserRoot userRoot) {
        System.out.println("UserDataAccessMapper.userRootToCompanyEntity -->1");
        CompanyRegistrationEntity entity = new CompanyRegistrationEntity();

        entity.setUserEntityId(UserID.random().value());
        entity.setCompanyName(userRoot.getUsername().value());
        entity.setEmail(userRoot.getEmail().getValue());
        entity.setEmailType(userRoot.getEmail().getType());
        entity.setPassword(userRoot.getPassword().getValue());
        entity.setStatus(userRoot.getStatus());
        System.out.println("UserDataAccessMapper.userRootToCompanyEntity -->2");

        UserAddressEntity addressEntity = addressToUserAddressEntity(userRoot.getAddress());
        entity.setAddress(addressEntity);
        System.out.println("UserDataAccessMapper.userRootToCompanyEntity -->3");

        PhoneNumberEntity phoneNumberEntity = phoneNumberToPhoneNumberEntity(userRoot.getPhoneNumber());
        entity.setPhoneNumberEntity(phoneNumberEntity);
        return entity;
    }

    public UserRoot companyEntityToUserRoot(CompanyRegistrationEntity userEntity) {
        System.out.println("UserDataAccessMapper.companyEntityToUserRoot -->1");
        return UserRoot.builder()
                .userID(UserID.of(userEntity.getUserEntityId()))
                .address(Address.of(
                        userEntity.getAddress().getState(),
                        userEntity.getAddress().getCountry(),
                        userEntity.getAddress().getCity(),
                        userEntity.getAddress().getStreet(),
                        userEntity.getAddress().getPostalCode()))
                .status(userEntity.getStatus())
                .roleAuthorithy(userEntity.getRoleAuthority())
                .password(Password.of(userEntity.getPassword()))
                .email(Email.of(userEntity.getEmailType(), userEntity.getEmail()))
                .phoneNumber(PhoneNumber.of(
                        userEntity.getPhoneNumberEntity().getType(),
                        userEntity.getPhoneNumberEntity().getCountryCode(),
                        userEntity.getPhoneNumberEntity().getNumber()))
                .build();
    }

    public Address userAdressEntityToAddress(UserAddressEntity address) {
        return Address.builder()
                .state(address.getState())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .build();
    }

    public UserAddressEntity addressToUserAddressEntity(Address address) {
        return UserAddressEntity.builder()
                .userAddressId(AddressID.random().value())
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
                .phoneNumberId(PhoneNumberID.random().value())
                .number(phoneNumber.getNumber())
                .countryCode(phoneNumber.getCountryCode())
                .type(phoneNumber.getType())
                .build();
    }
}