package com.intellibucket.company.service.primary.rest.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;

import com.intellibucket.company.service.domain.shell.dto.rest.command.product.*;
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
        log.info("Received product creation request: {}", command);
        ProductResponse productResponse = productCommandServiceAdapter.createProduct(command);
        log.info("Created product: {}", productResponse);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    //PRODUCT NAME STOCKQUANTITY PRICE  UPDATE
    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductUpdateCommand command) throws CompanyDomainException {
        log.info("Received product update request: {}", command);
        productCommandServiceAdapter.updateProduct(command);
        log.info("Updated product: {}", command);
    }

    @PutMapping("/activate")
    public void changeStatusToActivate(@RequestBody ProductStatusCommand command) throws CompanyDomainException {
        log.info("Received product status update request: {}", command);
        productCommandServiceAdapter.changeStatusToActivate(command);
        log.info("Updated product status: {}", command);
    }

    @PutMapping("/outofstock")
    public void changeStatusToOutOfStock(@RequestBody ProductStatusOutOfStockCommand command) throws CompanyDomainException {
        log.info("Received product status update request: {}", command);
        productCommandServiceAdapter.changeStatusToOutOfStock(command);
        log.info("Updated product status: {}", command);
    }

    //PRODUCT SOFT DELETE
    @PutMapping("/delete")
    public void deleteProduct(@RequestBody ProductDeleteCommand command) throws CompanyDomainException {
        log.info("Received product deletion request: {}", command);
        productCommandServiceAdapter.deleteProduct(command);
        log.info("Deleted product: {}", command);
    }


}
