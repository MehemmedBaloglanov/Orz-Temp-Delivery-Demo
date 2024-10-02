package com.intellibucket.user.service.connector.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractUserServiceConnector;
import org.springframework.stereotype.Component;

@Component
public class UserServiceConnector implements AbstractUserServiceConnector {
    @Override
    public UserAddress getUserPrimaryAddress(UserID userID) {
        return null;
    }
}
