package com.intellibucket.user.service.connector.adapter;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractUserServiceConnector;
import com.intellibucket.user.service.connector.dto.request.UserAddressResponse;
import com.intellibucket.user.service.connector.feign.UserServiceClient;
import com.intellibucket.user.service.connector.mapper.UserServiceConnectorDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class UserServiceConnector implements AbstractUserServiceConnector {
    private final UserServiceClient userServiceClient;
    private final UserServiceConnectorDataMapper userServiceConnectorDataMapper;

    @Override
    public UserAddress getUserPrimaryAddress(CustomerID customerID) {
        UserAddressResponse userPrimaryAddress = userServiceClient.getUserPrimaryAddress(customerID.value().toString());
        return userServiceConnectorDataMapper.userAddressResponseToUserAddress(userPrimaryAddress);
    }
}
