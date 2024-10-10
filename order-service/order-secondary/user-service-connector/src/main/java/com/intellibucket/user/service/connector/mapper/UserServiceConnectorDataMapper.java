package com.intellibucket.user.service.connector.mapper;

import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.user.service.connector.dto.request.UserAddressResponse;
import org.springframework.stereotype.Component;

@Component
public class UserServiceConnectorDataMapper {

    public UserAddress userAddressResponseToUserAddress(UserAddressResponse userPrimaryAddress) {
        return UserAddress.builder()
                .street(userPrimaryAddress.getStreet())
                .city(userPrimaryAddress.getCity())
                .state(userPrimaryAddress.getState())
                .build();
    }
}
