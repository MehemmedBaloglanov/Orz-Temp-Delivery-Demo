package com.intellibucket.order.service.domain.shell.outbox.model.payload.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderCompanyEventProduct {

    @JsonProperty
    private String id;

    @JsonProperty
    private String companyId;

    @JsonProperty
    private Integer quantity;
}
