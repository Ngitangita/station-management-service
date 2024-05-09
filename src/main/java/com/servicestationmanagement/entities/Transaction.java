package com.servicestationmanagement.entities;

import java.time.LocalDateTime;

import com.servicestationmanagement.enumes.TransactionType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Transaction {
    Integer id;
    TransactionType type;
    Double quantity;
    Double amount;
    LocalDateTime dateTransaction;
    Integer stationId;
    Integer productId;
}
