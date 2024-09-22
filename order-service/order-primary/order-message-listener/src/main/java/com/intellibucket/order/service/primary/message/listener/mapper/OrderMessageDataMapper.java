package com.intellibucket.order.service.primary.message.listener.mapper;

import com.intellibucket.kafka.order.avro.model.PaymentResponseAvroModel;
import com.intellibucket.order.service.domain.shell.dto.rest.response.PaymentResponse;

public class OrderMessageDataMapper {
    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel paymentResponseAvroModel) {
        return null;
    }
}
