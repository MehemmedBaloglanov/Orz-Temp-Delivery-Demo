package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.handler.message.OrderPaymentResponseSagaHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractPaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResponseMessageListener implements AbstractPaymentResponseMessageListener {
    private final OrderPaymentResponseSagaHandler orderPaymentResponseSagaHandler;

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) throws OrderDomainException {
        orderPaymentResponseSagaHandler.process(paymentResponse);
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) throws OrderDomainException {
        orderPaymentResponseSagaHandler.rollback(paymentResponse);
    }
}
