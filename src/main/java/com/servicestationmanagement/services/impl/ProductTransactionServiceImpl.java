package com.servicestationmanagement.services.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.servicestationmanagement.dtos.FuelTransactionInfo;
import com.servicestationmanagement.exception.InternalServerException;
import com.servicestationmanagement.repositories.ProductTransactionRepository;
import com.servicestationmanagement.services.ProductTransactionService;

@Service
public class ProductTransactionServiceImpl implements ProductTransactionService{
    private final ProductTransactionRepository productTransactionRepository;
    public ProductTransactionServiceImpl(ProductTransactionRepository productTransactionRepository) {
        this.productTransactionRepository = productTransactionRepository;
    }

    @Override
    public List<FuelTransactionInfo> findAllTransactionWithProductName() {
        try {
            return productTransactionRepository.findAllTransactionWithProductName();
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
