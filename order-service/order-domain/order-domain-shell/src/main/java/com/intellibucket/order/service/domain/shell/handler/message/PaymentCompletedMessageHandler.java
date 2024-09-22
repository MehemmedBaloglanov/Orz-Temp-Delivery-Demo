package com.intellibucket.order.service.domain.shell.handler.message;

import com.intellibucket.order.service.domain.shell.dto.rest.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCompletedMessageHandler {
    public void handle(PaymentResponse paymentResponse) {

    }
}
