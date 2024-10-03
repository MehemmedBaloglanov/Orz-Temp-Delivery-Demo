package com.intellibucket.user.service.connector.adapter;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractUserServiceConnector;
import org.springframework.stereotype.Component;

@Component
public class UserServiceConnector implements AbstractUserServiceConnector {
    @Override
    public UserAddress getUserPrimaryAddress(CustomerID customerID) {
        return UserAddress.builder()
                .city("Baku")
                .state("Azerbaijan")
                .street("Metbuat 17a")
                .build();
    }
}
