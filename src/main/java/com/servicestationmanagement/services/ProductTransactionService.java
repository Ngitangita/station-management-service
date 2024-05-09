package com.servicestationmanagement.services;

import java.util.List;

import com.servicestationmanagement.dtos.FuelTransactionInfo;

public interface ProductTransactionService {
    List<FuelTransactionInfo> findAllTransactionWithProductName();
}
