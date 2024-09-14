package com.intellibucket.order.service.domain.shell.port.input.listener.payment;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.shell.dto.response.PaymentResponse;

public interface PaymentMessageListener {

    OrderID paymentCompleted(PaymentResponse paymentResponse);

    OrderID paymentCancelled(PaymentResponse paymentResponse);

}
