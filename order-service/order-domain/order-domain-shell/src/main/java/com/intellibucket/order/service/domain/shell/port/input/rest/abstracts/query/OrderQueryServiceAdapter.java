package com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.query;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;

import java.util.List;

public interface OrderQueryServiceAdapter {

    TrackOrderResponse trackOrder(OrderTrackingQuery orderTrackingQuery);

    List<OrderResponse> orders(UserID userID);

    OrderResponse orderById(OrderID orderId);
}
