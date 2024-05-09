package com.servicestationmanagement.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicestationmanagement.dtos.BuyByMoney;
import com.servicestationmanagement.services.BuyByMoneyService;

@RestController
@CrossOrigin
@RequestMapping
public class BuyByMoneyRestController {
    private final BuyByMoneyService buyByMoneyService;

    public BuyByMoneyRestController(BuyByMoneyService buyByMoneyService) {
        this.buyByMoneyService = buyByMoneyService;
    }

    @PostMapping("/buy/money")
    public Map<String, String> buyByMoney(@RequestBody BuyByMoney request) {
        return buyByMoneyService.buyByMoney(request);
    }
}
