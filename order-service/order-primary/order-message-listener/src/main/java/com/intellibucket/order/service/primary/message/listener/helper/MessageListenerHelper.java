package com.intellibucket.order.service.primary.message.listener.helper;

import com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus;
import com.intellibucket.kafka.order.avro.model.delivery.DeliveryAvroStatus;
import com.intellibucket.kafka.order.avro.model.payment.PaymentAvroStatus;
import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import com.intellibucket.order.service.domain.core.valueobject.DeliveryStatus;
import com.intellibucket.order.service.domain.core.valueobject.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListenerHelper {

    public PaymentStatus paymentAvroStatusToPaymentPaymentStatus(PaymentAvroStatus status) {
        return switch (status) {
            case COMPLETED -> PaymentStatus.COMPLETED;
            case FAILED -> PaymentStatus.CANCELLED;
            default -> {
                log.error("Invalid payment status: {}", status);
                throw new IllegalArgumentException("Invalid payment status");
            }
        };
    }

    public DeliveryStatus deliveryAvroStatusToDeliveryStatus(DeliveryAvroStatus status) {
        return switch (status) {
            case DELIVERED -> DeliveryStatus.DELIVERED;
            case FAILED -> DeliveryStatus.FAILED;
            default -> {
                log.error("Invalid delivery status: {}", status);
                throw new IllegalArgumentException("Invalid delivery status");
            }
        };
    }

    public ApproveStatus approveAvroStatusToApproveStatus(ApproveAvroStatus status) {
        return switch (status) {
            case APPROVED -> ApproveStatus.APPROVED;
            case REJECTED -> ApproveStatus.REJECTED;
            default -> {
                log.error("Invalid approve status: {}", status);
                throw new IllegalArgumentException("Invalid approve status");
            }
        };
    }

}
