package com.intellibucket.order.service.domain.shell.port.input.rest.concretes;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.query.OrderQueryServiceAdapter;

import java.util.List;

public class OrderQueryServiceHandler implements OrderQueryServiceAdapter {
    @Override
    public TrackOrderResponse trackOrder(OrderTrackingQuery orderTrackingQuery) {
        return null;
    }

    @Override
    public List<OrderResponse> orders(UserID userID) {
        return List.of();
    }

    @Override
    public OrderResponse orderById(OrderID orderId) {
        return null;
    }

    @Override
    public List<OrderResponse> getUnassignOrders() {
        return List.of();
    }
}
