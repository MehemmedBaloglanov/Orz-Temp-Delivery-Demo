package com.intellibucket.order.service.domain.shell.port.input.rest.concretes;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
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

    @Override
    public TrackOrderResponse trackOrder(OrderTrackingQuery orderTrackingQuery) throws OrderDomainException {
        return orderTrackQueryHandler.handle(orderTrackingQuery);
    }

    @Override
    public List<OrderResponse> ordersByCustomer() throws OrderDomainException {
        return orderGetAllQueryHandler.handle();
    }

    @Override
    public OrderResponse orderById(OrderID orderId) throws OrderDomainException {
        return orderSingleFetchQueryHandler.handle(orderId);
    }
}
