package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderRefundRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyRefundEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderCompanyRefundEventPublisher;
import com.intellibucket.order.service.secondary.message.publisher.helper.OrderKafkaPublisherHelper;
import com.intellibucket.order.service.secondary.message.publisher.mapper.OrderMessagePublisherDataMapper;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCompanyRefundMessagePublisher implements AbstractOrderCompanyRefundEventPublisher {
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderKafkaPublisherHelper orderKafkaPublisherHelper;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;
    private final KafkaProducer<String, CompanyOrderRefundRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;

    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {
        OrderCompanyRefundEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderCompanyRefundEventPayload.class);

        log.info("Received OrderCompanyRefundMessagePublisher for order id: {}", payload.getOrderId());
        try {
            CompanyOrderRefundRequestAvroModel avroModel = orderMessagePublisherDataMapper.orderCompanyRefundEventPayloadToCompanyOrderRefundRequestAvroModel(payload);

            kafkaProducer.send(
                    orderServiceConfigData.getCompanyOrderRefundRequestTopicName(),
                    UUID.randomUUID().toString(),
                    avroModel,
                    orderKafkaPublisherHelper.getCallback(avroModel, message, payload.getOrderId(), outboxCallback));

            log.info("OrderCompanyRefundMessagePublisher sent to Kafka for order id: {}", payload.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending OrderCompanyRefundMessagePublisher to kafka with order id: {}, error: {}", payload.getOrderId(), e.getMessage());
        }
    }
}
