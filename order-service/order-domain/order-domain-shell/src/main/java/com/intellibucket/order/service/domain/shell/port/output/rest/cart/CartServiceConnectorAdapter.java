package com.intellibucket.order.service.domain.shell.port.output.rest.cart;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.cart.service.connector.dto.CartResponse;

import java.util.List;

public interface CartServiceConnectorAdapter {
    List<CartResponse> findUserCartItems(UserID userID);
}
