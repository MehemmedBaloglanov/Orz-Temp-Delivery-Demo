package com.intellibucket.order.service.primary.message.listener.mapper;

import com.intellibucket.kafka.order.avro.model.DeliveryResponseAvroModel;
import com.intellibucket.kafka.order.avro.model.PaymentResponseAvroModel;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;

public class OrderMessageDataMapper {
    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel paymentResponseAvroModel) {
        return null;
    }

    public DeliveryResponse deliveredResponseAvroModelToDeliveryResponse(DeliveryResponseAvroModel deliveryResponseAvroModel) {
        return null;
    }
}
