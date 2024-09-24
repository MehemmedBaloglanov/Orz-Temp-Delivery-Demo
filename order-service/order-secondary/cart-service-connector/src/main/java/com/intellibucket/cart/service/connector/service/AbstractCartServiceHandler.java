package com.intellibucket.cart.service.connector.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;

import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCartServiceConnector;

import java.util.List;

public class AbstractCartServiceHandler implements AbstractCartServiceConnector {
    @Override
    public List<CartResponse> findUserCartItems(UserID userID) {
        return List.of();
    }
}
