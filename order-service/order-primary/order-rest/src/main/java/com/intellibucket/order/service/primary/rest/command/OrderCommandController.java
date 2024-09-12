package com.intellibucket.order.service.primary.rest.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCreateCommand;
import com.intellibucket.order.service.domain.shell.port.input.rest.command.OrderCommandService;
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
    private final OrderCommandService orderCommandService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderCreateCommand createOrderCommand) {
        orderCommandService.createOrder(createOrderCommand);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }
    @GetMapping("/unassigned")
    public ResponseEntity<List<OrderCreateCommand>> getUnassignedOrders() {
        List<OrderCreateCommand> unassignedOrders = orderCommandService.getUnassignedOrders();
        if (unassignedOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(unassignedOrders);
    }
    @PostMapping("/assign")
    public ResponseEntity<String> assignOrder(@RequestParam UUID orderId) {
        try {
            orderCommandService.assignOrder(orderId);
            return ResponseEntity.ok("Order successfully assigned to agent.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assigning order.");
        }
    }
    @PutMapping("/cancel")
    public ResponseEntity<String> cancelOrder(OrderCancelCommand orderCancelCommand) {
        orderCommandService.cancelOrder(orderCancelCommand);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }

}
