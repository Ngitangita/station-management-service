package com.servicestationmanagement.entities;

import com.servicestationmanagement.enumes.FuelType;
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
public class Product {
    Integer id;
    FuelType name;
    Double unitPrice;
    Double quantity;
    public double calculateQuantityByAmount(double amount) {
        return amount / unitPrice;
    }

    public double calculateAmountByQuantity(double quantity){
        return quantity * unitPrice;
    }

}
