package com.intellibucket.company.service.connector;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CompanyServiceConnector implements AbstractCompanyServiceConnector {
    private final CompanyClient companyClient;

    @Autowired
    public CompanyServiceConnector(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }
    public CompanyResponse getCompanyById(String companyId) {

        return companyClient.getCompanyById(companyId);
    }

//    public CompanyResponse createCompany(CompanyRequest companyRequest) {
//
//        return companyClient.createCompany(companyRequest);
//    }


    @Override
    public ProductResponse getProductInformation(ProductID productID) {
        // Call the company client to get product information by productID
        ProductResponse productResponse = companyClient.getProductById(productID);

        // Assuming some post-processing if needed, or return the response directly
        return productResponse;
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
