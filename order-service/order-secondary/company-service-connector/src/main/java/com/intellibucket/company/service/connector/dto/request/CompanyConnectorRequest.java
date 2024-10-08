package com.intellibucket.company.service.connector.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanyConnectorRequest {
    private String companyId;
    private List<ProductConnectorRequest> products;
}
