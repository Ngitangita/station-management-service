package com.servicestationmanagement.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicestationmanagement.dtos.UpdateStockRequest;
import com.servicestationmanagement.services.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionRestController {
    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/update/stock")
    public Map<String, String> updateStock(@RequestBody UpdateStockRequest request) {
            return transactionService.updateStock(request);
    }
}
