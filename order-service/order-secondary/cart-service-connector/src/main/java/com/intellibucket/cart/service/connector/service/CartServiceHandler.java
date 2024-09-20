package com.intellibucket.cart.service.connector.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.cart.service.connector.dto.CartResponse;
import com.intellibucket.order.service.domain.shell.port.output.rest.cart.CartServiceAdapter;

import java.util.List;

public class CartServiceHandler implements CartServiceAdapter {
    @Override
    public List<CartResponse> findUserCartItems(UserID userID) {
        return List.of();
    }
}
