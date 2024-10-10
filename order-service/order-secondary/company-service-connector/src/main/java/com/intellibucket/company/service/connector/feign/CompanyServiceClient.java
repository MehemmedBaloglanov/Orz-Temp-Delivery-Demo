package com.intellibucket.company.service.connector.feign;

import com.intellibucket.company.service.connector.dto.response.CompanyConnectorResponse;
import com.intellibucket.company.service.connector.dto.request.CompanyConnectorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "company-service", url = "${company-service.url}")
public interface CompanyServiceClient {

    @GetMapping("/api/1.0/products/private/information/single")
    CompanyConnectorResponse getProductsInformation(CompanyConnectorRequest request);

    @GetMapping("/api/1.0/products/private/information/list")
    List<CompanyConnectorResponse> getProductsInformation(List<CompanyConnectorRequest> requests);

}
