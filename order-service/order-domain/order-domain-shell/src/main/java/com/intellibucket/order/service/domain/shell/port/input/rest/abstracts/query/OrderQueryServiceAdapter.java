package com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.query;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;

import java.util.List;

public interface OrderQueryServiceAdapter {

    TrackOrderResponse trackOrder(OrderTrackingQuery orderTrackingQuery) throws OrderDomainException;

    List<OrderResponse> ordersByCustomer() throws OrderDomainException;

    OrderResponse orderById(OrderID orderId) throws OrderDomainException;

}
