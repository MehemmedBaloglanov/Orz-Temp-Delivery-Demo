package com.intellibucket.order.service.domain.shell.port.output.rest.company;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.connector.dto.ProductResponse;

public interface CompanyServiceConnectorAdapter {
    ProductResponse getProductInformation(ProductID productID);

}
