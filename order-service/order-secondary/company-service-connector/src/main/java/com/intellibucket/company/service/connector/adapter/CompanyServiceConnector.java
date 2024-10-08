package com.intellibucket.company.service.connector.adapter;

import com.intellibucket.company.service.connector.CompanyConnectorClient;
import com.intellibucket.company.service.connector.dto.request.CompanyConnectorRequest;
import com.intellibucket.company.service.connector.dto.response.CompanyConnectorResponse;
import com.intellibucket.company.service.connector.mapper.CompanyConnectorDataMapper;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequest;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyServiceConnector implements AbstractCompanyServiceConnector {
    private final CompanyConnectorClient companyConnectorClient;
    private final CompanyConnectorDataMapper companyConnectorDataMapper;


    @Override
    public ProductResponse getProductInformation(CompanyRequest companyRequest) {
        CompanyConnectorRequest request = companyConnectorDataMapper.companyRequestToCompanyConnectorRequest(companyRequest);
        CompanyConnectorResponse productsInformation = companyConnectorClient.getProductsInformation(request);
        return companyConnectorDataMapper.companyConnectorResponseToProductResponse(productsInformation);
    }

    @Override
    public List<ProductResponse> getProductsInformation(List<CompanyRequest> companyRequests) {
        List<CompanyConnectorRequest> request = companyRequests.stream().map(companyConnectorDataMapper::companyRequestToCompanyConnectorRequest).toList();
        List<CompanyConnectorResponse> productsInformation = companyConnectorClient.getProductsInformation(request);
        return productsInformation.stream().map(companyConnectorDataMapper::companyConnectorResponseToProductResponse).toList();
    }
}
