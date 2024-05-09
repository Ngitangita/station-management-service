package com.servicestationmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicestationmanagement.dtos.DailySalesSummary;
import com.servicestationmanagement.services.DailySalesSummaryService;

@RestController
@CrossOrigin
@RequestMapping
public class DailySalesSummaryRestController {
    private final DailySalesSummaryService dailySalesSummaryService;

    public DailySalesSummaryRestController(DailySalesSummaryService dailySalesSummaryService) {
        this.dailySalesSummaryService = dailySalesSummaryService;
    }

    @GetMapping("/daily/sales")
    public List<DailySalesSummary> getDailySalesSummary(){
        return dailySalesSummaryService.findDailySalesSummary();
    }
}
