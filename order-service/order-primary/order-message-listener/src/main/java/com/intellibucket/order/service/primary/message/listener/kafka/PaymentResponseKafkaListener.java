package com.intellibucket.order.service.primary.message.listener.kafka;

import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.payment.PaymentResponseAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.valueobject.PaymentStatus;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractPaymentResponseMessageListener;
import com.intellibucket.order.service.primary.message.listener.mapper.OrderMessageListenerDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {
    private final OrderMessageListenerDataMapper orderMessageListenerDataMapper;
    private final AbstractPaymentResponseMessageListener paymentResponseMessageListener;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.order-service-consumer-group-id}", topics = "${order-service.payment-response-topic-name}")
    public void receive(@Payload List<PaymentResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of PaymentResponseAvroModel received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(paymentResponseAvroModel -> {
            try {

                PaymentResponse paymentResponse = orderMessageListenerDataMapper.paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel);
                if (PaymentStatus.COMPLETED == paymentResponse.getStatus()) {

                    log.info("Processing successful payment for order id: {}", paymentResponse.getOrderId());
                    paymentResponseMessageListener.paymentCompleted(paymentResponse);

                } else if (PaymentStatus.CANCELLED == paymentResponse.getStatus()) {

                    log.info("Processing unsuccessful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                    paymentResponseMessageListener.paymentCancelled(paymentResponse);

                } else {
                    log.error("Unknown payment status for order id: {}", paymentResponseAvroModel.getOrderId());
                    throw new OrderDomainException("Unknown payment status for order id: " + paymentResponseAvroModel.getOrderId());
                }
            } catch (OptimisticLockingFailureException e) {
                //NO-OP for optimistic lock. This means another thread finished the work, do not throw error to prevent reading the data from kafka again!
                log.error("Caught optimistic locking exception in PaymentResponseKafkaListener for order id: {}", paymentResponseAvroModel.getOrderId());
            } catch (OrderNotFoundException e) {
                //NO-OP for OrderNotFoundException
                log.error("No order found for order id: {}", paymentResponseAvroModel.getOrderId());
            } catch (OrderDomainException e) {
                log.error("Ops order unknown exception for order id: {}", paymentResponseAvroModel.getOrderId());
            }
        });
    }
}
