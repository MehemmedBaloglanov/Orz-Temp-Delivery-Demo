package com.intellibucket.order.service.domain.shell.port.output.connector;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;

import java.util.List;

public interface AbstractCartServiceConnector {
    List<CartResponse> findUserCartItems(UserID userID);
}
