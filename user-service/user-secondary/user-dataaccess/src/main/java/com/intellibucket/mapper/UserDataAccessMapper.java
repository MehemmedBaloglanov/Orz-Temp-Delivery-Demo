package com.intellibucket.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.model.UserEntity;
import com.intellibucket.model.UserJpaAddress;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import lombok.Getter;

@Getter
public class UserDataAccessMapper {
    public UserEntity userRootToUserEntity(UserRoot userRoot) {
        return UserEntity.builder()
                .uuid(userRoot.getUserID().value())
                .role(userRoot.getRoleAuthorithy())
                .email(userRoot.getEmail())
                .phoneNumber(userRoot.getPhoneNumber())
                .status(userRoot.getStatus())
                .address(AddressToUserJpaAdress(userRoot.getAddress()))
                .password(userRoot.getPassword())
                .build();
    }

    public UserRoot userEntityToUserRoot(UserEntity userEntity) {
        return UserRoot.builder()
                .userID(UserID.of(userEntity.getUuid()))
                .address(UserJpaAdresstoAddress(userEntity.getAddress()))
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .roleAuthorithy(userEntity.getRole())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }


    public Address UserJpaAdresstoAddress(UserJpaAddress userJpaAddress) {
        return Address.builder()
                .state(userJpaAddress.getState())
                .street(userJpaAddress.getStreet())
                .city(userJpaAddress.getCity())
                .build();
    }

    public UserJpaAddress AddressToUserJpaAdress(Address address) {
        return UserJpaAddress.builder()
                .state(address.getState())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }
}
