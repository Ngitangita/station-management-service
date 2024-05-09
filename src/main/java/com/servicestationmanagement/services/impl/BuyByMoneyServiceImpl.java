package com.servicestationmanagement.services.impl;
import com.servicestationmanagement.entities.Transaction;
import com.servicestationmanagement.enumes.TransactionType;
import com.servicestationmanagement.exception.InternalServerException;
import com.servicestationmanagement.exception.NotFoundException;
import org.springframework.stereotype.Service;

import com.servicestationmanagement.dtos.BuyByMoney;
import com.servicestationmanagement.repositories.TransactionRepository;
import com.servicestationmanagement.services.BuyByMoneyService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import com.servicestationmanagement.services.ProductService;
import com.servicestationmanagement.services.StationService;

@Service
public class BuyByMoneyServiceImpl implements BuyByMoneyService{

    private final ProductService productService;
    private final StationService stationService;
    private final TransactionRepository transactionRepository;
    private final Connection connection;

    public BuyByMoneyServiceImpl(ProductService productService, StationService stationService, TransactionRepository transactionRepository, Connection connection) {
        this.productService = productService;
        this.stationService = stationService;
        this.transactionRepository = transactionRepository;
        this.connection = connection;
    }

    @Override
    public Map<String, String> buyByMoney(BuyByMoney request) {
        try {
            connection.setAutoCommit(false);
            var product = productService.findProductById(request.productId());
            var station = stationService.findStationById(request.stationId());
            if (product == null) {
                throw new NotFoundException("Product not found");
            }
            if (station == null) {
                throw new NotFoundException("Station not found");
            }
            var requiredQuantity = product.calculateQuantityByAmount(request.amount());
            if (product.getQuantity() < requiredQuantity) {
                throw new NotFoundException("Insufficient quantity in stock");
            }
            var newQuantity = product.getQuantity() - requiredQuantity;
            product.setQuantity(newQuantity);
            productService.updateProduct(product.getId(), product);
            transactionRepository.create(
                    new Transaction()
                            .setAmount(request.amount())
                            .setQuantity(requiredQuantity)
                            .setStationId(station.getId())
                            .setProductId(product.getId())
                            .setType(TransactionType.SORTIE)
                            .setDateTransaction(LocalDateTime.now())
            );
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new InternalServerException("Failed to rollback transaction", ex);
            }
            throw new InternalServerException("Failed to create transaction", e);
        }
        return Map.of("message", "Purchase successful");
    }

}
