package com.intellibucket.order.service.domain.shell.port.output.connector;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;

public interface AbstractUserServiceConnector {
    UserAddress getUserPrimaryAddress(UserID userID);
}
