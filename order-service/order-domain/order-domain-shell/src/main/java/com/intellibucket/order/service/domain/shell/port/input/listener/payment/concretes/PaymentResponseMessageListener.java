package com.intellibucket.order.service.domain.shell.port.input.listener.payment.concretes;

import com.intellibucket.order.service.domain.shell.dto.rest.response.PaymentResponse;
import com.intellibucket.order.service.domain.shell.handler.message.PaymentCancelledMessageHandler;
import com.intellibucket.order.service.domain.shell.handler.message.PaymentCompletedMessageHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.payment.abstracts.AbstractPaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResponseMessageListener implements AbstractPaymentResponseMessageListener {
    private final PaymentCompletedMessageHandler paymentCompletedMessageHandler;
    private final PaymentCancelledMessageHandler paymentCancelledMessageHandler;

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        paymentCompletedMessageHandler.handle(paymentResponse);
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        paymentCancelledMessageHandler.handle(paymentResponse);
    }
}
