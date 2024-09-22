package com.intellibucket.cart.service.connector.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.connectors.CartResponse;

import com.intellibucket.order.service.domain.shell.port.output.rest.cart.CartServiceConnectorAdapter;

import java.util.List;

public class CartServiceHandler implements CartServiceConnectorAdapter {
    @Override
    public List<CartResponse> findUserCartItems(UserID userID) {
        return List.of();
    }
}
