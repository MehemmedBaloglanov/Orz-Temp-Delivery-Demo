package com.intellibucket.company.service.connector;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service", url = "${company-service.url}")
public interface CompanyClient {

    @GetMapping("/api/v1/companies/{companyId}")
    CompanyResponse getCompanyById(@PathVariable("companyId") String companyId);

    @GetMapping("/api/v1/products/{productId}")
    ProductResponse getProductById(@PathVariable("productId") ProductID productId);

}
