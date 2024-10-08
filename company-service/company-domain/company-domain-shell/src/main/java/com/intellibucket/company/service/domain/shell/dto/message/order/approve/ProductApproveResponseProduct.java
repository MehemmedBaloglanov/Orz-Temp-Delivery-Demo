package com.intellibucket.company.service.domain.shell.dto.message.order.approve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductApproveResponseProduct {

    @JsonProperty
    private UUID productId;

    @JsonProperty
    private UUID companyId;

    @JsonProperty
    private Integer quantity;
}
