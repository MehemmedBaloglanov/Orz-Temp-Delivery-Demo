package com.intellibucket.order.service.primary.rest.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderAssignCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command.OrderCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final OrderCommandServiceAdapter orderCommandServiceAdapter;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder() throws OrderDomainException {
        OrderResponse order = orderCommandServiceAdapter.createOrder();
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/reject")
    public ResponseEntity<OrderResponse> rejectOrder(@RequestBody OrderRejectCommand orderRejectCommand) {
        OrderResponse response = orderCommandServiceAdapter.rejectOrder(orderRejectCommand);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/assign")
    public ResponseEntity<String> assignOrder(@RequestBody OrderAssignCommand orderAssignCommand) throws OrderDomainException {
        orderCommandServiceAdapter.assignOrder(orderAssignCommand);
        return ResponseEntity.ok("Order successfully assigned to agent.");

    }
    @PutMapping("/cancel")
    public ResponseEntity<String> cancelOrder(OrderCancelCommand orderCancelCommand) throws OrderDomainException {
        orderCommandServiceAdapter.cancelOrder(orderCancelCommand);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }

}
