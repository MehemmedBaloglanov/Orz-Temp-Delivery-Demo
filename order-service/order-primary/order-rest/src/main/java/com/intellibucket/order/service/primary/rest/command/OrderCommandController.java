package com.intellibucket.order.service.primary.rest.command;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderConfirmCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
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
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //    TODO Customer yox Company e aiddi

    @GetMapping("/reject")
    public ResponseEntity<String> rejectOrder(@RequestBody OrderRejectCommand orderRejectCommand) throws OrderDomainException {
        orderCommandServiceAdapter.rejectOrder(orderRejectCommand);
        return ResponseEntity.ok("Order successfully assigned to agent.");
    }

    //    TODO Customer yox Company e aiddi

    @PostMapping("/assign")
    public ResponseEntity<String> confirmOrder(@RequestBody OrderConfirmCommand orderConfirmCommand) throws OrderDomainException {
        orderCommandServiceAdapter.confirmOrder(orderConfirmCommand);
        return ResponseEntity.ok("Order successfully assigned.");

    }

    @PutMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody OrderCancelCommand orderCancelCommand) throws OrderDomainException {
        orderCommandServiceAdapter.cancelOrder(orderCancelCommand);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }

}
