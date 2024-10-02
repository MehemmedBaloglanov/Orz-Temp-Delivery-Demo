package com.intellibucket.user.service.connector.adapter;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyServiceConnector implements AbstractCompanyServiceConnector {
    @Override
    public ProductResponse getProductInformation(ProductID productID) {
        return null;
    }

    @Override
    public List<ProductResponse> getProductsInformation(List<ProductID> productIDs) {
        return List.of();
    }
}
