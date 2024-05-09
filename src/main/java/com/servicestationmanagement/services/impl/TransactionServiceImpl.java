package com.servicestationmanagement.services.impl;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import com.servicestationmanagement.enumes.FuelType;
import org.springframework.stereotype.Service;

import com.servicestationmanagement.dtos.UpdateStockRequest;
import com.servicestationmanagement.entities.Product;
import com.servicestationmanagement.entities.Station;
import com.servicestationmanagement.entities.Transaction;
import com.servicestationmanagement.enumes.TransactionType;
import com.servicestationmanagement.exception.BadRequestException;
import com.servicestationmanagement.exception.InternalServerException;
import com.servicestationmanagement.exception.NotFoundException;
import com.servicestationmanagement.repositories.TransactionRepository;
import com.servicestationmanagement.services.ProductService;
import com.servicestationmanagement.services.StationService;
import com.servicestationmanagement.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final StationService stationService;
    private final ProductService productService;
    private final Connection connection;

    public TransactionServiceImpl(TransactionRepository transactionRepository, StationService stationService, ProductService productService, Connection connection) {
        this.transactionRepository = transactionRepository;
        this.stationService = stationService;
        this.productService = productService;
        this.connection = connection;
    }

    @Override
    public Map<String, String> updateStock(UpdateStockRequest toCreate) {
        var station = stationService.findStationById(toCreate.stationId());
        if (station == null) {
            throw new NotFoundException("Station not found");
        }
        try {
            connection.setAutoCommit(false);
            for (var newQuantityUpdate : toCreate.productQuantityUpdates()) {
                var productId = newQuantityUpdate.productId();
                var quantity = newQuantityUpdate.quantity();
                if (productId == null || quantity == null) {
                    throw new BadRequestException("Invalid product ID or quantity");
                }
                var product = productService.findProductById(productId);
                var newQuantity = getNewQuantity(product, station, quantity);
                product.setQuantity(newQuantity);
                productService.updateProduct(product.getId(), product);
                transactionRepository.create(
                        new Transaction()
                                .setAmount(newQuantityUpdate.buyingPrice())
                                .setQuantity(newQuantityUpdate.quantity())
                                .setStationId(station.getId())
                                .setProductId(productId)
                                .setType(TransactionType.ENTRY)
                                .setDateTransaction(LocalDateTime.now())
                );
            }
            connection.commit();
            return Map.of("message", "Stock updated successfully");
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new InternalServerException("Failed to rollback transaction");
            }
            throw new InternalServerException("Failed to update stock");
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ignore) {}
            }
        }
    }

    private double getNewQuantity(Product product, Station station, Double quantity) {
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        var fuelType = product.getName();
        double maxVolume = switch (fuelType) {
            case FuelType.DIESEL -> station.getMaxVolumeDiesel();
            case FuelType.PETROL -> station.getMaxVolumePetrol();
            case FuelType.GASOLINE -> station.getMaxVolumeGasoline();
        };
        var maxVolumeInLitres = maxVolume * 1000;
        var currentQuantity = product.getQuantity();
        var newQuantity = currentQuantity + quantity;
        if (newQuantity > maxVolumeInLitres) {
            throw new BadRequestException("Exceeds maximum volume");
        }
        return newQuantity;
    }

}
