//package com.intellibucket.company.service.connector;
//
//import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
//import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FeignService {
//
//    private final CompanyClient companyClient;
//
//    @Autowired
//    public FeignService(CompanyClient companyClient) {
//        this.companyClient = companyClient;
//    }
//
//
//    public CompanyResponse getCompanyDetails(String companyId) {
//        return companyClient.getCompanyById(companyId);
//    }
//    public CompanyStatus getCompanyStatus(String companyId) {
//        CompanyResponse companyResponse = companyClient.getCompanyById(companyId);
//        return companyResponse.getStatus();
//    }
//}
//
