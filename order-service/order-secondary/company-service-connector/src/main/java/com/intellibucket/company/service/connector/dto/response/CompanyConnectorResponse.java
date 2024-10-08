package com.intellibucket.company.service.connector.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyConnectorResponse {
    private UUID productId;
    private UUID companyId;
    private Integer quantity;
    private BigDecimal price;
    private CompanyConnectorStatus companyStatus;
    private ProductConnectorStatus productStatus;

}
