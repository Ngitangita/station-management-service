package com.servicestationmanagement.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicestationmanagement.dtos.BuyInLiterRequest;
import com.servicestationmanagement.services.BuyInLitersService;

@RestController
@CrossOrigin
@RequestMapping
public class BuyInLitersRestController {
    private final BuyInLitersService buyInLitersService;

    public BuyInLitersRestController(BuyInLitersService buyInLitersService) {
        this.buyInLitersService = buyInLitersService;
    }

    @PostMapping("/buy/liters")
    public Map<String, String> buyInLiters(@RequestBody BuyInLiterRequest request) {
        return buyInLitersService.buyInLiters(request);
    }
}
