package com.intellibucket.order.service.primary.rest.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductUpdateCommand;
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
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateCommand command) throws CompanyDomainException {
        ProductResponse productResponse = productCommandServiceAdapter.createProduct(command);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    //PRODUCT NAME STOCKQUANTITY PRICE  UPDATE
    @PutMapping("/command")
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
