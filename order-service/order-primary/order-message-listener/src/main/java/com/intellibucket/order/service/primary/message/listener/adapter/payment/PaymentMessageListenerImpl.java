package com.intellibucket.order.service.primary.message.listener.adapter.payment;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.shell.dto.response.PaymentResponse;
import com.intellibucket.order.service.domain.shell.port.input.listener.payment.PaymentMessageListener;

public class PaymentMessageListenerImpl implements PaymentMessageListener {
    @Override
    public OrderID paymentCompleted(PaymentResponse paymentResponse) {
        return null;
    }

    @Override
    public OrderID paymentCancelled(PaymentResponse paymentResponse) {
        return null;
    }
}
