package com.intellibucket.order.service.primary.rest.query;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.query.OrderQueryServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderQueryController {

    private final OrderQueryServiceAdapter orderQueryServiceAdapter;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID orderId) {
        OrderResponse orderResponse = orderQueryServiceAdapter.orderById(OrderID.of(orderId));
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId() {
        //FIXme
        UserID userId = UserID.random();
        List<OrderResponse> orders = orderQueryServiceAdapter.orders(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/track")
    public ResponseEntity<TrackOrderResponse> trackOrder(OrderTrackingQuery orderTrackingQuery) {
        TrackOrderResponse response = orderQueryServiceAdapter.trackOrder(orderTrackingQuery);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/unassign")
    public ResponseEntity<List<OrderResponse>> getUnassignOrder() {
        List<OrderResponse> response = orderQueryServiceAdapter.getUnassignOrders();
        return ResponseEntity.ok(response);
    }

}
