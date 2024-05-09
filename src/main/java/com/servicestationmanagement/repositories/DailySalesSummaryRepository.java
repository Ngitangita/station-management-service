package com.servicestationmanagement.repositories;

import com.servicestationmanagement.dtos.DailySalesSummary;
import java.sql.SQLException;
import java.util.List;

public interface DailySalesSummaryRepository {
    List<DailySalesSummary> findDailySalesSummary() throws SQLException;
}
