package com.intellibucket.order.service.primary.rest.command;

import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command.OrderCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final OrderCommandServiceAdapter orderCommandServiceAdapter;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder() {
        OrderResponse order = orderCommandServiceAdapter.createOrder();
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/unassigned")
    public ResponseEntity<List<OrderResponse>> getUnassignedOrders() {
        List<OrderResponse> unassignedOrders = orderCommandServiceAdapter.getUnassignedOrders();
        return ResponseEntity.ok(unassignedOrders);
    }
    @PostMapping("/assign")
    public ResponseEntity<String> assignOrder(@RequestParam UUID orderId) {
        orderCommandServiceAdapter.assignOrder(orderId);
        return ResponseEntity.ok("Order successfully assigned to agent.");

    }
    @PutMapping("/cancel")
    public ResponseEntity<String> cancelOrder(OrderCancelCommand orderCancelCommand) {
        orderCommandServiceAdapter.cancelOrder(orderCancelCommand);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }

}
