package com.intellibucket.company.service.connector.adapter;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.domain.exception.DomainException;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequest;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//@Component
public class TestCompanyServiceConnector implements AbstractCompanyServiceConnector {
    @Override
    public ProductResponse getProductInformation(CompanyRequest companyRequest) throws DomainException {


        return ProductResponse.builder()
                .price(Money.of(BigDecimal.valueOf(10)))
                .companyId(CompanyID.of("01924d1f-a2f8-7825-9730-15ef6d4738"))
                .productId(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(500)
                .productStatus(ProductStatus.ACTIVE)
                .companyStatus(CompanyStatus.ACTIVE)
                .build();
    }

    @Override
    public List<ProductResponse> getProductsInformation(List<CompanyRequest> productIDs) {
        ProductResponse productResponse3 = ProductResponse.builder()
                .price(Money.of(BigDecimal.valueOf(10)))
                .companyId(CompanyID.of("01924d1f-a2f8-7825-9730-15ef6d4738"))
                .productId(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(500)
                .productStatus(ProductStatus.ACTIVE)
                .companyStatus(CompanyStatus.ACTIVE)
                .build();

        ProductResponse productResponse1 = ProductResponse.builder()
                .price(Money.of(BigDecimal.valueOf(10)))
                .companyId(CompanyID.of("01924d1f-a2f8-7825-9730-15ef6d4738"))
                .productId(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(500)
                .productStatus(ProductStatus.ACTIVE)
                .companyStatus(CompanyStatus.ACTIVE)
                .build();

        ProductResponse productResponse2 = ProductResponse.builder()
                .price(Money.of(BigDecimal.valueOf(10)))
                .companyId(CompanyID.of("01924d1f-a2f8-7825-9730-15ef6d4738"))
                .productId(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(500)
                .productStatus(ProductStatus.ACTIVE)
                .companyStatus(CompanyStatus.ACTIVE)
                .build();

        return List.of(productResponse2, productResponse1, productResponse3);
    }
}
