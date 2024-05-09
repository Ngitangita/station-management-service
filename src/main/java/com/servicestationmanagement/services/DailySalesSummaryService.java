package com.servicestationmanagement.services;

import java.util.List;

import com.servicestationmanagement.dtos.DailySalesSummary;

public interface DailySalesSummaryService {
    List<DailySalesSummary> findDailySalesSummary();
}
