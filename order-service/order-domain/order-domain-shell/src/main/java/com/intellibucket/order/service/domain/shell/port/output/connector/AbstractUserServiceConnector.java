package com.intellibucket.order.service.domain.shell.port.output.connector;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;

public interface AbstractUserServiceConnector {
    UserAddress getUserPrimaryAddress(CustomerID customerID);
}
