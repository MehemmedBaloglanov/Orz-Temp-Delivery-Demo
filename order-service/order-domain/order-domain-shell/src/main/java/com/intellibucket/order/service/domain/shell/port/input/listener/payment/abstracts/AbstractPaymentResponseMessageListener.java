package com.intellibucket.order.service.domain.shell.port.input.listener.payment.abstracts;

import com.intellibucket.order.service.domain.shell.dto.rest.response.PaymentResponse;

public interface AbstractPaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
