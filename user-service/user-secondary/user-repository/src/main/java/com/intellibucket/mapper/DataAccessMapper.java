package com.intellibucket.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.model.CustomerRegistrationEntity;
import com.intellibucket.model.UserAddressEntity;
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
    public static CustomerRegistrationEntity toCustomerRegistrationEntity(UserRoot userRoot, String firstName, String lastName) {
        CustomerRegistrationEntity entity = new CustomerRegistrationEntity();
        entity.setCustomerEntityId(UUID.randomUUID());
        entity.setUsername(Username.generate(firstName, lastName).value());
        entity.setPassword(userRoot.getPassword().getValue());
        entity.setEmail(userRoot.getEmail().getValue());
        entity.setType(userRoot.getEmail().getType());
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
    public static UserRoot toUserRoot(CustomerRegistrationEntity entity) {
        return UserRoot.builder()
                .userID(UserID.of(entity.getCustomerEntityId())) // ok
                .username(Username.of(entity.getUsername())) // ok
                .password(Password.of(entity.getPassword())) // ok
                .email(Email.of(entity.getType(), entity.getEmail())) // ok
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
                .city(entity.getCity())
                .country(entity.getCountry())
                .build();
    }

    // OKEYDIR
    public static UserAddressEntity toAddressEntity(UserAddressEntity entity, Address address) {
        entity.setStreet(address.getStreet());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setPostalCode(address.getPostalCode());
        entity.setCountry(address.getCountry());
        return entity;
    }
}