package com.intellibucket.order.service.domain.shell.port.output.rest.company;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;

public interface CompanyServiceConnectorAdapter {
    ProductResponse getProductInformation(ProductID productID);
}
