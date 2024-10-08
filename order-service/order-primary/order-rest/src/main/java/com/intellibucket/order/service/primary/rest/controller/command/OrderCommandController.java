package com.intellibucket.order.service.primary.rest.controller.command;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.*;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command.OrderCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final OrderCommandServiceAdapter orderCommandServiceAdapter;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder() throws OrderDomainException {
        OrderResponse order = orderCommandServiceAdapter.createOrder();
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    //    TODO Customer yox Company e aiddi

    @PostMapping("/reject")
    public ResponseEntity<String> rejectOrder(@RequestBody OrderRejectCommand command) throws OrderDomainException {
        orderCommandServiceAdapter.rejectOrder(command);
        return ResponseEntity.ok("Order successfully assigned to agent.");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody OrderCancelCommand command) throws OrderDomainException {
        orderCommandServiceAdapter.cancelOrder(command);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }

    @PostMapping("deliver")
    public ResponseEntity<String> startDeliveryOrder(@RequestBody OrderStartDeliveryCommand command) throws OrderDomainException {
        orderCommandServiceAdapter.startDeliveryOrder(command);
        return new ResponseEntity<>("Order delivery started successfully", HttpStatus.OK);
    }

    @PostMapping("/prepared")
    public ResponseEntity<String> prepareOrder(@RequestBody OrderPrepareCommand command) throws OrderDomainException {
        orderCommandServiceAdapter.preparedOrder(command);
        return new ResponseEntity<>("Order prepared successfully", HttpStatus.OK);
    }

}
