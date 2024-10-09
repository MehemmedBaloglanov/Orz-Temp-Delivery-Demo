package com.intellibucket.company.service.message.listener.kafka;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;
import com.intellibucket.company.service.domain.shell.port.input.listener.abstracts.AbstractOrderApproveResponseMessageListener;
import com.intellibucket.company.service.message.listener.mapper.CompanyMessageListenerDataMapper;
import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
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
public class ProductStockUpdateKafkaListener implements KafkaConsumer<CompanyOrderApproveRequestAvroModel> {
    private final CompanyMessageListenerDataMapper companyMessagePublisherDataMapper;
    private final AbstractOrderApproveResponseMessageListener abstractOrderApproveResponseMessageListener;

    @Override
    @KafkaListener(groupId = "#product-service.product-service-group-id", topics = "#product-service.product-stock-update-topic")
    public void receive(
            @Payload List<CompanyOrderApproveRequestAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(message -> {
            ProductApproveResponse orderProductsResponse = companyMessagePublisherDataMapper.productStockUpdateRequestAvroModelToOrderProductsResponse(message);
            try {
                abstractOrderApproveResponseMessageListener.approveOrder(orderProductsResponse);
            } catch (CompanyDomainException e) {
                log.error(e.getMessage());
            }
        });
    }
}
