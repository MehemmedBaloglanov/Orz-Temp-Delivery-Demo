package com.intellibucket.order.service.primary.message.listener.kafka;

import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractOrderApproveResponseMessageListener;
import com.intellibucket.order.service.primary.message.listener.mapper.OrderMessageListenerDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApproveResponseKafkaListener implements KafkaConsumer<CompanyOrderApproveResponseAvroModel> {
    private final OrderMessageListenerDataMapper orderMessageListenerDataMapper;
    private final AbstractOrderApproveResponseMessageListener orderMessageListener;

    @Override
    @KafkaListener(groupId = "${kafka-consumer-config.order-service-consumer-group-id}", topics = "${order-service.company-order-approve-response-topic-name}")
    public void receive(@Payload List<CompanyOrderApproveResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of CompanyOrderApproveResponseAvroModel received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(avroModel -> {
            ApproveResponse approveResponse = orderMessageListenerDataMapper.companyOrderApproveResponseAvroModelToApproveResponse(avroModel);
            try {
                if (approveResponse.getStatus() == ApproveStatus.APPROVED) {
                    orderMessageListener.orderApproved(approveResponse);
                } else {
                    orderMessageListener.orderDeclined(approveResponse);
                }
            } catch (OrderNotFoundException e) {
                //NO-OP for OrderNotFoundException
                log.error("No order found for order id: {}", approveResponse.getOrderId());
            } catch (OrderDomainException e) {
                log.error("Ops order unknown exception for order id: {}", approveResponse.getOrderId());
            }
        });

    }
}
