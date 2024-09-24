package com.intellibucket.order.service.primary.rest.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.ProductCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandServiceAdapter productCommandServiceAdapter;

    //PRODUCT CREATE
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct() throws CompanyDomainException {
        ProductResponse productResponse = productCommandServiceAdapter.createProduct();
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    //PRODUCT UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id) throws CompanyDomainException {
        ProductResponse productResponse = productCommandServiceAdapter.updateProduct(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    //PRODUCT DELETE
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) throws CompanyDomainException {
        productCommandServiceAdapter.deleteProduct(id);
    }




    //todo 1)changeDelete 2)changeActive 3)changeOutOfStock
}
