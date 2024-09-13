package com.intellibucket.order.service.domain.shell.port.input.listener.payment;

import com.intellibucket.order.service.domain.shell.dto.response.PaymentResponse;

public interface PaymentMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
