package com.servicestationmanagement.dtos;

public record ProductQuantityUpdate(
        Integer productId,
        Double quantity,
        Double amount,
        Double buyingPrice) {}
