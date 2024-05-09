package com.servicestationmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicestationmanagement.dtos.FuelTransactionInfo;
import com.servicestationmanagement.services.ProductTransactionService;

@RestController
@CrossOrigin
public class ProductTransactionRestController {
    private final ProductTransactionService productTransactionService;


    public ProductTransactionRestController(ProductTransactionService productTransactionService) {
        this.productTransactionService = productTransactionService;
    }

    @GetMapping("/transaction/with/product/name")
    public List<FuelTransactionInfo> findAllTransactionWithProductName(){
        return productTransactionService.findAllTransactionWithProductName();
    }
}
