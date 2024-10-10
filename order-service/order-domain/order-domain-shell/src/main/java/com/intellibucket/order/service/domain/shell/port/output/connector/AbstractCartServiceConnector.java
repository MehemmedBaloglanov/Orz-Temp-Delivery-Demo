package com.intellibucket.order.service.domain.shell.port.output.connector;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;

import java.util.List;

public interface AbstractCartServiceConnector {
    List<CartResponse> getUserCart(CustomerID customerID);
}
