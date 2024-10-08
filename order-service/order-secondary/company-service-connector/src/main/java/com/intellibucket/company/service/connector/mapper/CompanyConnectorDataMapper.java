package com.intellibucket.company.service.connector.mapper;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.connector.dto.request.CompanyConnectorRequest;
import com.intellibucket.company.service.connector.dto.request.ProductConnectorRequest;
import com.intellibucket.company.service.connector.dto.response.CompanyConnectorResponse;
import com.intellibucket.company.service.connector.dto.response.CompanyConnectorStatus;
import com.intellibucket.company.service.connector.dto.response.ProductConnectorStatus;
import com.intellibucket.domain.exception.DomainException;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequest;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequestProduct;
import org.springframework.stereotype.Component;

@Component
public class CompanyConnectorDataMapper {
    public CompanyConnectorRequest companyRequestToCompanyConnectorRequest(CompanyRequest companyRequest) {
        return CompanyConnectorRequest.builder()
                .companyId(companyRequest.getCompanyID().value().toString())
                .products(companyRequest.getProducts().stream().map(this::companyRequestProductToProductConnectorRequest).toList())
                .build();
    }

    public ProductConnectorRequest companyRequestProductToProductConnectorRequest(CompanyRequestProduct requestProduct) {
        return ProductConnectorRequest.builder()
                .productId(requestProduct.getProductID().value().toString())
                .quantity(requestProduct.getQuantity())
                .build();
    }

    public ProductResponse companyConnectorResponseToProductResponse(CompanyConnectorResponse companyConnectorResponse) {
        return ProductResponse.builder()
                .productId(ProductID.of(companyConnectorResponse.getProductId()))
                .companyId(CompanyID.of(companyConnectorResponse.getCompanyId()))
                .price(Money.of(companyConnectorResponse.getPrice()))
                .quantity(companyConnectorResponse.getQuantity())
                .companyStatus(companyConnectorStatusToCompanyStatus(companyConnectorResponse.getCompanyStatus()))
                .productStatus(productConnectorStatusToProductStatus(companyConnectorResponse.getProductStatus()))
                .build();
    }

    public CompanyStatus companyConnectorStatusToCompanyStatus(CompanyConnectorStatus companyConnectorStatus) {
        return switch (companyConnectorStatus) {
            case ACTIVE -> CompanyStatus.ACTIVE;
            case INACTIVE -> CompanyStatus.INACTIVE;
            case NOT_FOUND -> CompanyStatus.NOT_FOUND;
            default -> throw new IllegalArgumentException("Unknown CompanyConnectorStatus: " + companyConnectorStatus);
        };
    }

    public CompanyConnectorStatus companyStatusToCompanyConnectorStatus(CompanyStatus companyStatus) {
        return switch (companyStatus) {
            case ACTIVE -> CompanyConnectorStatus.ACTIVE;
            case INACTIVE -> CompanyConnectorStatus.INACTIVE;
            case NOT_FOUND -> CompanyConnectorStatus.NOT_FOUND;
            default -> throw new IllegalArgumentException("Unknown CompanyStatus: " + companyStatus);
        };
    }

    public ProductConnectorStatus productStatusToProductConnectorStatus(ProductStatus productStatus) {
        return switch (productStatus) {
            case ACTIVE -> ProductConnectorStatus.ACTIVE;
            case OUT_OF_STOCK -> ProductConnectorStatus.OUT_OF_STOCK;
            case NOT_FOUND -> ProductConnectorStatus.NOT_FOUND;
            default -> throw new IllegalArgumentException("Unknown CompanyStatus: " + productStatus);
        };
    }

    public ProductStatus productConnectorStatusToProductStatus(ProductConnectorStatus productConnectorStatus) {
        return switch (productConnectorStatus) {
            case ACTIVE -> ProductStatus.ACTIVE;
            case OUT_OF_STOCK -> ProductStatus.OUT_OF_STOCK;
            case NOT_FOUND -> ProductStatus.NOT_FOUND;
            default -> throw new IllegalArgumentException("Unknown CompanyConnectorStatus: " + productConnectorStatus);
        };
    }
}
