//package com.intellibucket.order.service.primary.rest.query;
//
//import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
//import com.intellibucket.company.service.domain.shell.dto.rest.query.CompanyGetByIDQuery;
//import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
//import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.CompanyQueryServiceAdapter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static javax.security.auth.callback.ConfirmationCallback.OK;
//
//@RestController
//@RequestMapping("/api/1.0/company")
//@RequiredArgsConstructor
//public class CompanyQueryController {
//
//    private final CompanyQueryServiceAdapter companyQueryServiceAdapter;
//
//    //GET COMPANY WITH ID
//    @GetMapping()
//    public ResponseEntity<CompanyResponse> getCompanyById(@RequestBody CompanyGetByIDQuery id) throws CompanyDomainException {
//        CompanyResponse companyResponse = companyQueryServiceAdapter.getCompanyById(id);
//        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
//    }
//}
