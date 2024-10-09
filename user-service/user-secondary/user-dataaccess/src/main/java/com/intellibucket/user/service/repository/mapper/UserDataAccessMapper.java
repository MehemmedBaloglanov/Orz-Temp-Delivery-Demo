package com.intellibucket.user.service.repository.mapper;

import com.intelliacademy.orizonroute.identity.AddressID;
import com.intelliacademy.orizonroute.identity.user.PhoneNumberID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.response.UserAddressResponse;
import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import com.intellibucket.user.service.repository.model.PhoneNumberEntity;
import com.intellibucket.user.service.repository.model.UserAddressEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDataAccessMapper {
    public CustomerRegistrationEntity userRootToCustomerEntity(UserRoot userRoot) {
        log.debug("userRoot: {}", userRoot);
        return CustomerRegistrationEntity.builder()
                .userEntityId(userRoot.getUserID().value())
                .lastName(userRoot.getLastName())
                .firstName(userRoot.getFirstName())
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
                .userEntityId(userRoot.getUserID().value())
                .username(userRoot.getUsername().value())
                .companyName(userRoot.getCompanyName())
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
                .username(Username.of(userEntity.getUsername()))
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .userID(UserID.of(userEntity.getUserEntityId()))
                .address(userJpaAdresstoAddress(userEntity.getAddress()))
                .status(userEntity.getStatus())
                .roleAuthorithy(userEntity.getRoleAuthority())
                .password(Password.of(userEntity.getPassword()))
                .email(Email.of(userEntity.getEmailType(), userEntity.getEmail()))
                .phoneNumber(phoneNumberEntityToPhoneNumber(userEntity.getPhoneNumberEntity()))
                .build();
    }

    public UserRoot companyEntityToUserRoot(CompanyRegistrationEntity userEntity) {
        return UserRoot.builder()
                .username(Username.of(userEntity.getUsername()))
                .userID(UserID.of(userEntity.getUserEntityId()))
                .address(userJpaAdresstoAddress(userEntity.getAddress()))
                .status(userEntity.getStatus())
                .companyName(userEntity.getCompanyName())
                .roleAuthorithy(userEntity.getRoleAuthority())
                .password(Password.of(userEntity.getPassword()))
                .email(Email.of(userEntity.getEmailType(), userEntity.getEmail()))
                .phoneNumber(phoneNumberEntityToPhoneNumber(userEntity.getPhoneNumberEntity()))
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

    public UserAddressResponse customerAddressToUserAddressResponse(CustomerRegistrationEntity customerEntity) {
        return new UserAddressResponse(
                customerEntity.getAddress().getState(),
                customerEntity.getAddress().getCountry(),
                customerEntity.getAddress().getCity(),
                customerEntity.getAddress().getStreet(),
                customerEntity.getAddress().getPostalCode()
        );
    }

    public UserAddressResponse companyAddressToUserAddressResponse(CompanyRegistrationEntity companyEntity) {
        return new UserAddressResponse(
                companyEntity.getAddress().getState(),
                companyEntity.getAddress().getCountry(),
                companyEntity.getAddress().getCity(),
                companyEntity.getAddress().getStreet(),
                companyEntity.getAddress().getPostalCode()
        );
    }
}