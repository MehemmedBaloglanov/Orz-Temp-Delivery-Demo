package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.handler.message.PaymentCancelledMessageHandler;
import com.intellibucket.order.service.domain.shell.handler.message.PaymentCompletedMessageHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractPaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResponseMessageListener implements AbstractPaymentResponseMessageListener {
    private final PaymentCompletedMessageHandler paymentCompletedMessageHandler;
    private final PaymentCancelledMessageHandler paymentCancelledMessageHandler;

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) throws OrderDomainException {
        paymentCompletedMessageHandler.handle(paymentResponse);
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) throws OrderDomainException {
        paymentCancelledMessageHandler.handle(paymentResponse);
    }
}
