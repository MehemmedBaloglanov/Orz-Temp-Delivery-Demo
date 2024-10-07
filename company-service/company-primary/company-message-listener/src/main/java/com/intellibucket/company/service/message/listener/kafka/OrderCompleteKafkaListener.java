package com.intellibucket.company.service.message.listener.kafka;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.message.order.complete.OrderCompletedResponse;
import com.intellibucket.company.service.domain.shell.port.input.listener.abstracts.AbstractOrderCompletedResponseMessageListener;
import com.intellibucket.company.service.message.listener.mapper.CompanyMessagePublisherDataMapper;
import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderRefundRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.company.OrderCompletedRequestAvroModel;
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
public class OrderCompleteKafkaListener implements KafkaConsumer<OrderCompletedRequestAvroModel>{
    private final CompanyMessagePublisherDataMapper companyMessagePublisherDataMapper;

    private final AbstractOrderCompletedResponseMessageListener  abstractOrderCompletedResponseMessageListener;

    @Override
    public void receive(@Payload List<OrderCompletedRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(orderCompletedRequestAvroModel -> {
            OrderCompletedResponse response = companyMessagePublisherDataMapper.orderCompletedRequestAvroModelToOrderCompletedResponse(orderCompletedRequestAvroModel);
            try{
                abstractOrderCompletedResponseMessageListener.completeOrder(response);
            } catch (CompanyDomainException e) {
                log.error(e.getMessage());
            }
        });
    }
}
