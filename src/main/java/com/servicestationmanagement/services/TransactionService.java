package com.servicestationmanagement.services;

import java.util.Map;

import com.servicestationmanagement.dtos.UpdateStockRequest;

public interface TransactionService {
    Map<String , String> updateStock(UpdateStockRequest toCreate);
}
