package com.intellibucket.order.service.primary.message.listener.mapper;

import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel;
import com.intellibucket.kafka.order.avro.model.delivery.DeliveryResponseAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundResponseAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentResponseAvroModel;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentRefundResponse;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.primary.message.listener.helper.MessageListenerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMessageListenerDataMapper {
    private final MessageListenerHelper messageListenerHelper;

    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .orderId(paymentResponseAvroModel.getOrderId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .price(paymentResponseAvroModel.getPrice())
                .createdAt(paymentResponseAvroModel.getCreatedAt())
                .status(messageListenerHelper.paymentAvroStatusToPaymentPaymentStatus(paymentResponseAvroModel.getPaymentStatus()))
                .failureMessage(paymentResponseAvroModel.getFailureMessages())
                .build();
    }

    public DeliveryResponse deliveredResponseAvroModelToDeliveryResponse(DeliveryResponseAvroModel deliveryResponseAvroModel) {
        return DeliveryResponse.builder()
                .orderId(deliveryResponseAvroModel.getOrderId())
                .status(messageListenerHelper.deliveryAvroStatusToDeliveryStatus(deliveryResponseAvroModel.getDeliveryStatus()))
                .failureMessage(deliveryResponseAvroModel.getFailureMessage())
                .build();
    }

    public ApproveResponse companyOrderApproveResponseAvroModelToApproveResponse(CompanyOrderApproveResponseAvroModel avroModel) {
        return ApproveResponse.builder()
                .orderId(avroModel.getOrderId())
                .failureMessage(avroModel.getFailureMessage())
                .status(messageListenerHelper.approveAvroStatusToApproveStatus(avroModel.getStatus()))
                .createdAt(avroModel.getCreatedAt())
                .build();
    }

    public PaymentRefundResponse paymentRefundResponseAvroModelToPaymentRefundResponse(PaymentRefundResponseAvroModel avroModel) {
        return PaymentRefundResponse.builder()
                .orderId(avroModel.getOrderId())
                .failureMessage(avroModel.getFailureMessage())
                .createdAt(avroModel.getCreatedAt())
                .build();
    }
}
