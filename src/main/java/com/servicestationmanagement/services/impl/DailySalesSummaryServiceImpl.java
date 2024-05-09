package com.servicestationmanagement.services.impl;
import org.springframework.stereotype.Service;

import com.servicestationmanagement.dtos.DailySalesSummary;
import com.servicestationmanagement.exception.InternalServerException;
import com.servicestationmanagement.repositories.DailySalesSummaryRepository;
import com.servicestationmanagement.services.DailySalesSummaryService;
import java.sql.SQLException;
import java.util.List;

@Service
public class DailySalesSummaryServiceImpl implements DailySalesSummaryService{
    private final DailySalesSummaryRepository dailySalesSummaryRepository;

    public DailySalesSummaryServiceImpl(DailySalesSummaryRepository dailySalesSummaryRepository) {
        this.dailySalesSummaryRepository = dailySalesSummaryRepository;
    }

    @Override
    public List<DailySalesSummary> findDailySalesSummary() {
            try {
                return dailySalesSummaryRepository.findDailySalesSummary();
            } catch (SQLException e) {
                throw new InternalServerException(e.getMessage());
            }
    }
}
