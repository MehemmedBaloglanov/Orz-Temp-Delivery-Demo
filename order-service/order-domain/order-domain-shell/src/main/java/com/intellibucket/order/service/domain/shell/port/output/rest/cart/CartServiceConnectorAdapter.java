package com.intellibucket.order.service.domain.shell.port.output.rest.cart;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.connectors.CartResponse;

import java.util.List;

public interface CartServiceConnectorAdapter {
    List<CartResponse> findUserCartItems(UserID userID);
}
