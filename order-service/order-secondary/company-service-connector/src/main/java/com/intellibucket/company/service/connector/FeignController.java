//package com.intellibucket.company.service.connector;
//
//import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
//import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FeignController {
//
//    private final FeignService feignService;
//
//    @Autowired
//    public FeignController(FeignService feignService) {
//        this.feignService = feignService;
//    }
//
//    // Expose an API endpoint to fetch company details
//    @GetMapping("/api/companies/{companyId}")
//    public CompanyResponse getCompanyById(@PathVariable("companyId") String companyId) {
//        return feignService.getCompanyDetails(companyId);
//    }
//    @GetMapping("/api/companies/{companyId}/status")
//    public CompanyStatus getCompanyStatus(@PathVariable("companyId") String companyId) {
//        return feignService.getCompanyStatus(companyId);
//    }
//}
