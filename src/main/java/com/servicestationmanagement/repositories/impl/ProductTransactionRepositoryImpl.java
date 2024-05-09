package com.servicestationmanagement.repositories.impl;
import com.servicestationmanagement.dtos.FuelTransactionInfo;
import com.servicestationmanagement.enumes.TransactionType;
import com.servicestationmanagement.repositories.ProductTransactionRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductTransactionRepositoryImpl implements ProductTransactionRepository {
    private final Connection connection;

    public ProductTransactionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FuelTransactionInfo> findAllTransactionWithProductName() throws SQLException {
            final var query = "SELECT  t.date_transaction,p.name, t.type, t.quantity  FROM products AS p INNER JOIN transactions as t ON p.id = t.product_id";
            final var fuelTransactionInfos = new ArrayList<FuelTransactionInfo>();
            try (final var stmt = connection.prepareStatement(query);
                 final var rs = stmt.executeQuery()
            ) {
                while (rs.next()) {
                    fuelTransactionInfos.add(mapResultSetToFuelTransactionInfo(rs));
                }
            }
            return fuelTransactionInfos;
    }

    private FuelTransactionInfo mapResultSetToFuelTransactionInfo(ResultSet rs) throws SQLException {
        return new FuelTransactionInfo(
                rs.getTimestamp("date_transaction").toLocalDateTime(),
                rs.getString("name"),
                TransactionType.valueOf(rs.getString("type")),
                rs.getDouble("quantity")
        );
    }

}
