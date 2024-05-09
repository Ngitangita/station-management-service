package com.servicestationmanagement.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servicestationmanagement.enumes.TransactionType;

public record FuelTransactionInfo(
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime transactionDateTime,
        String fuelType,
        TransactionType transactionType,
         Double quantity
) {}
