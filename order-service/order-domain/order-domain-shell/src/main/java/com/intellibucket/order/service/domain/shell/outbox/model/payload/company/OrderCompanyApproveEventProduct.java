package com.intellibucket.order.service.domain.shell.outbox.model.payload.company;

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
public class OrderCompanyApproveEventProduct {

    @JsonProperty
    private UUID productId;

    @JsonProperty
    private UUID companyId;

    @JsonProperty
    private Integer quantity;
}
