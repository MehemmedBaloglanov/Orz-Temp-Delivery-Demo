package com.intellibucket.order.service.primary.rest.controller.query;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.query.OrderQueryServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderQueryController {
    private final OrderQueryServiceAdapter orderQueryServiceAdapter;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID orderId) throws OrderDomainException {
        OrderResponse orderResponse = orderQueryServiceAdapter.orderById(OrderID.of(orderId));
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId() throws OrderDomainException {
        List<OrderResponse> orders = orderQueryServiceAdapter.ordersByCustomer();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/track")
    public ResponseEntity<TrackOrderResponse> trackOrder(OrderTrackingQuery orderTrackingQuery) throws OrderDomainException {
        TrackOrderResponse response = orderQueryServiceAdapter.trackOrder(orderTrackingQuery);
        return ResponseEntity.ok(response);
    }

}
