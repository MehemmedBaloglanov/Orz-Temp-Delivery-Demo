package com.intellibucket.order.service.domain.shell.port.output.connector;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;

import java.util.List;

public interface AbstractCompanyServiceConnector {
    ProductResponse getProductInformation(ProductID productID);

    List<ProductResponse> getProductsInformation(List<ProductID> productIDs);
    CompanyResponse getCompanyById(String companyId);

}
