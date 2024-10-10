package com.intellibucket.company.service.connector.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductConnectorRequest {
    private String productId;
    private Integer quantity;
}
