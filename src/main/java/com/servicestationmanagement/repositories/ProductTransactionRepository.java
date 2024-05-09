package com.servicestationmanagement.repositories;

import com.servicestationmanagement.dtos.FuelTransactionInfo;
import java.sql.SQLException;
import java.util.List;

public interface ProductTransactionRepository {
     List<FuelTransactionInfo> findAllTransactionWithProductName() throws SQLException;
}
