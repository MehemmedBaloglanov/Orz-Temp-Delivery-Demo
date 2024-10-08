package com.intellibucket.company.service.connector;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CompanyServiceConnector implements AbstractCompanyServiceConnector {
    @Override
    public ProductResponse getProductInformation(ProductID productID) {
        return null;
    }

    @Override
    public List<ProductResponse> getProductsInformation(List<ProductID> productIDs) {

        CompanyResponse companyResponse = CompanyResponse.builder()
                .companyID(CompanyID.of("01924d1f-a2f8-7825-9730-15ebaf6d4738"))
                .status(CompanyStatus.ACTIVE)
                .build();

        ProductResponse productResponse = ProductResponse.builder()
                .price(Money.of(BigDecimal.valueOf(10)))
                .company(companyResponse)
                .productId(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(500)
                .status(ProductStatus.ACTIVE)
                .build();

        return List.of(productResponse);
    }
}
