package com.intellibucket.order.service.primary.rest.query;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.ProductQueryServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryServiceAdapter productQueryServiceAdapter;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) throws CompanyDomainException {
        ProductResponse productResponse = productQueryServiceAdapter.getProductById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

}
