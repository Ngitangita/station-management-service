package com.servicestationmanagement.services;

import com.servicestationmanagement.dtos.BuyByMoney;
import java.util.Map;

public interface BuyByMoneyService {
    Map<String, String> buyByMoney(BuyByMoney request); 
}
