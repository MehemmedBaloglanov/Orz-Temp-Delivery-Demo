package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentRefundResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRefundResponseHandler {

    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderDomainService orderDomainService;

    @Transactional
    public void handle(PaymentRefundResponse paymentRefundResponse) throws OrderDomainException {
        log.error("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        OrderID orderID = OrderID.of(paymentRefundResponse.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        orderDomainService.orderCancel(orderRoot, paymentRefundResponse.getFailureMessage());
        OrderRoot orderRoot1 = orderRepositoryHelper.saveOrder(orderRoot);
        log.info("Payment refund response handled for order: {}", orderRoot1);
    }
}
