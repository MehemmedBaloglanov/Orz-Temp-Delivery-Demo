package com.intellibucket.order.service.domain.shell.port.input.rest.concretes;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.handler.query.OrderCompanyUnassignedQueryHandler;
import com.intellibucket.order.service.domain.shell.handler.query.OrderGetAllQueryHandler;
import com.intellibucket.order.service.domain.shell.handler.query.OrderSingleFetchQueryHandler;
import com.intellibucket.order.service.domain.shell.handler.query.OrderTrackQueryHandler;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.query.OrderQueryServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceHandler implements OrderQueryServiceAdapter {
    private final OrderTrackQueryHandler orderTrackQueryHandler;
    private final OrderGetAllQueryHandler orderGetAllQueryHandler;
    private final OrderSingleFetchQueryHandler orderSingleFetchQueryHandler;
    private final OrderCompanyUnassignedQueryHandler orderCompanyUnassignedQueryHandler;

    @Override
    public TrackOrderResponse trackOrder(OrderTrackingQuery orderTrackingQuery) throws OrderNotFoundException {
        return orderTrackQueryHandler.handle(orderTrackingQuery);
    }

    @Override
    public List<OrderResponse> orders(UserID userID) {
        return orderGetAllQueryHandler.handle();
    }

    @Override
    public OrderResponse orderById(OrderID orderId) {
        return orderSingleFetchQueryHandler.handle(orderId);
    }

    @Override
    public List<OrderResponse> getUnassignOrders() {
        return orderCompanyUnassignedQueryHandler.handle();
    }
}
