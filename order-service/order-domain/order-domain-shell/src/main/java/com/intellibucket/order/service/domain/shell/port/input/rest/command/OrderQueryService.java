package com.intellibucket.order.service.domain.shell.port.input.rest.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.response.TrackOrderResponse;

import java.util.List;

public interface OrderQueryService {
    TrackOrderResponse trackOrder(OrderTrackingQuery orderTrackingQuery);
    List<OrderResponse> getOrders (UserID userId);
    OrderResponse findByOrderId(OrderID orderId);
}
