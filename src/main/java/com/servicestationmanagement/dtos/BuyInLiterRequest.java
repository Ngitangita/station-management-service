package com.servicestationmanagement.dtos;

public record BuyInLiterRequest(
        Integer stationId,
        Integer productId,
        Double quantity
) {}
