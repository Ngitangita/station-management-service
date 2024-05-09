package com.servicestationmanagement.dtos;

public record BuyByMoney(
        Integer stationId,
        Integer productId,
        Double amount) {}
