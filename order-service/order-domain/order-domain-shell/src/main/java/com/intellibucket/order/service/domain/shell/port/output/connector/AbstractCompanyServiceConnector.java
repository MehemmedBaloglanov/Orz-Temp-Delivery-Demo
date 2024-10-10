package com.intellibucket.order.service.domain.shell.port.output.connector;

import com.intellibucket.domain.exception.DomainException;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequest;

import java.util.List;

public interface AbstractCompanyServiceConnector {
    ProductResponse getProductInformation(CompanyRequest companyRequest) throws DomainException;

    List<ProductResponse> getProductsInformation(List<CompanyRequest> productIDs);

}
