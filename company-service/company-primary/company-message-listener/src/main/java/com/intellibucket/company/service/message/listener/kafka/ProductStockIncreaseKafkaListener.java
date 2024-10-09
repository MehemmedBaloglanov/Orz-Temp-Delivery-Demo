package com.intellibucket.company.service.message.listener.kafka;

import com.intellibucket.company.service.domain.shell.dto.message.order.refund.OrderCompanyRefundResponse;
import com.intellibucket.company.service.domain.shell.port.input.listener.abstracts.AbstractOrderRefundResponseMessageListener;
import com.intellibucket.company.service.message.listener.mapper.CompanyMessageListenerDataMapper;
import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderRefundRequestAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockIncreaseKafkaListener implements KafkaConsumer<CompanyOrderRefundRequestAvroModel> {
    private final CompanyMessageListenerDataMapper companyMessageListenerDataMapper;
    private final AbstractOrderRefundResponseMessageListener orderRefundResponseMessageListener;

    @Override
    public void receive(@Payload List<CompanyOrderRefundRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        messages.forEach(message -> {
            OrderCompanyRefundResponse orderCompanyRefundResponse = companyMessageListenerDataMapper.companyOrderRefundRequestAvroModelToOrderRefundResponse(message);
            orderCompanyRefundResponse.getProducts().forEach(orderRefundResponseMessageListener::refundOrder
            );
        });
    }
}
