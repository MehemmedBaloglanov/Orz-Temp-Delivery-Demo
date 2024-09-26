package com.intellibucket.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.model.CompanyRegistrationEntity;
import com.intellibucket.model.CustomerRegistrationEntity;
import com.intellibucket.model.UserAddressEntity;
import com.intellibucket.model.UserRegistrationEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataAccessMapper {
    // OKEYDIR - Bir tək rolu və statusu set etmək servisin öhdəliyindədir.
    public static UserRegistrationEntity toUserRegistrationEntity(UserRoot userRoot, String firstName, String lastName) {
        CustomerRegistrationEntity entity = new CustomerRegistrationEntity();
        entity.setUserEntityId(UUID.randomUUID());
        entity.setUsername(Username.generate(firstName, lastName).value());
        entity.setPassword(userRoot.getPassword().getValue());
        entity.setEmail(userRoot.getEmail().getValue());
        entity.setEmailType(userRoot.getEmail().getType());
        entity.setPhoneNumber(userRoot.getPhoneNumber().getNumber());
        entity.setRoleAuthority(userRoot.getRoleAuthorithy()); // It will be passed directly inside the service
        entity.setStatus(userRoot.getStatus()); // It will be passed directly inside the service

        UserAddressEntity addressEntity = new UserAddressEntity();
        addressEntity.setStreet(userRoot.getAddress().getStreet());
        addressEntity.setCity(userRoot.getAddress().getCity());
        addressEntity.setState(userRoot.getAddress().getState());
        addressEntity.setPostalCode(userRoot.getAddress().getPostalCode());
        addressEntity.setCountry(userRoot.getAddress().getCountry());

        entity.setAddress(addressEntity);

        return entity;
    }

    // OKEYDIR
    public static UserRoot customerRegistrationEntitytoUserRoot(CustomerRegistrationEntity entity) {
        return UserRoot.builder()
                .userID(UserID.of(entity.getUserEntityId())) // ok
                .username(Username.of(entity.getUsername())) // ok
                .password(Password.of(entity.getPassword())) // ok
                .email(Email.of(entity.getEmailType(), entity.getEmail())) // ok
                .phoneNumber(PhoneNumber.of(entity.getPhoneNumberEntity().getType(), entity.getPhoneNumberEntity().getCountryCode(), entity.getPhoneNumberEntity().getNumber())) // ok
                .address(Address.of(entity.getAddress().getState(), entity.getAddress().getCountry(), entity.getAddress().getCity(), entity.getAddress().getStreet(), entity.getAddress().getPostalCode())) // ok
                .roleAuthorithy(RoleAuthorithy.valueOf(entity.getRoleAuthority().name())) // ok
                .status(Status.valueOf(entity.getStatus().name())) // ok
                .build();
    }

    // OKEYDIR
    public static UserRoot companyRegistrationEntitytoUserRoot(CompanyRegistrationEntity entity) {
        return UserRoot.builder()
                .userID(UserID.of(entity.getUserEntityId())) // ok
                .username(Username.generate(entity.getCompanyName())) // ok
                .password(Password.of(entity.getPassword())) // ok
                .email(Email.of(entity.getEmailType(), entity.getEmail())) // ok
                .phoneNumber(PhoneNumber.of(entity.getPhoneNumberEntity().getType(), entity.getPhoneNumberEntity().getCountryCode(), entity.getPhoneNumberEntity().getNumber())) // ok
                .address(Address.of(entity.getAddress().getState(), entity.getAddress().getCountry(), entity.getAddress().getCity(), entity.getAddress().getStreet(), entity.getAddress().getPostalCode())) // ok
                .roleAuthorithy(RoleAuthorithy.valueOf(entity.getRoleAuthority().name())) // ok
                .status(Status.valueOf(entity.getStatus().name())) // ok
                .build();
    }

    // OKEYDIR
    public static Address toValueObjectAddress(UserAddressEntity entity) {
        return Address.builder()
                .state(entity.getState())
                .country(entity.getCountry())
                .city(entity.getCity())
                .street(entity.getStreet())
                .postalCode(entity.getPostalCode())
                .build();
    }

    // OKEYDIR
    public static UserAddressEntity toAddressEntity(UserAddressEntity entity, Address address) {
        entity.setState(address.getState());
        entity.setCountry(address.getCountry());
        entity.setCity(address.getCity());
        entity.setStreet(address.getStreet());
        entity.setPostalCode(address.getPostalCode());
        return entity;
    }
}