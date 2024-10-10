package com.intellibucket.company.service.primary.rest.query;

import com.intellibucket.company.service.domain.core.exception.ProductNotFoundException;
import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductGetByIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.query.ProductListWithIdQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.ProductQueryServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
public class ProductQueryController{

    private final ProductQueryServiceAdapter productQueryServiceAdapter;
    //GET PRODUCT BY ID
    @GetMapping("/id")
    public ResponseEntity<ProductResponse> getProductById(@RequestBody ProductGetByIdQuery productGetByIdQuery) throws ProductNotFoundException {
        ProductResponse productResponse = productQueryServiceAdapter.getProductById(productGetByIdQuery);
        return  new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseForOrder>> listProduct (@RequestBody ProductListWithIdQuery productListWithIdQuery) throws ProductNotFoundException{
        List<ProductResponseForOrder> productResponse = productQueryServiceAdapter.listProduct(productListWithIdQuery);
        return  new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


    //todo 1)AllProduct

}
