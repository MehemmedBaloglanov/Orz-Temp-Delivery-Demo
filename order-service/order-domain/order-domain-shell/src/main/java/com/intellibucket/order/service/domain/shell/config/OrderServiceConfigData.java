package com.intellibucket.order.service.domain.shell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-service")
public class OrderServiceConfigData {
    private String paymentRequestTopicName;
    private String paymentRefundRequestTopicName;

    private String startDeliveryRequestTopicName;
    private String deliveryResponseTopicName;

    private String companyOrderApproveRequestTopicName;
    private String companyOrderApproveResponseTopicName;
    private String companyOrderRefundRequestTopicName;

    private String completeOrderRequestTopicName;
}
