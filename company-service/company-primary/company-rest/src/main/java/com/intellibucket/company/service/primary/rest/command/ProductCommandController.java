package com.intellibucket.company.service.primary.rest.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.ProductCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/1.0/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandServiceAdapter productCommandServiceAdapter;

    //PRODUCT CREATE
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateCommand command) throws CompanyDomainException {
        log.info("Created product: {}", command);
        ProductResponse productResponse = productCommandServiceAdapter.createProduct(command);
        log.info("Created product: {}", productResponse);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    //PRODUCT NAME STOCKQUANTITY PRICE  UPDATE
    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductUpdateCommand command) throws CompanyDomainException {
        productCommandServiceAdapter.updateProduct(command);
    }

    @PutMapping ("/status")
    public void changeStatus(@RequestBody ProductStatusCommand command) throws CompanyDomainException {
        productCommandServiceAdapter.changeStatus(command);
    }


    //PRODUCT SOFT DELETE
    @DeleteMapping("/{id}")
    public void deleteProduct(@RequestBody ProductDeleteCommand command) throws CompanyDomainException {
        productCommandServiceAdapter.deleteProduct(command);
    }


}
