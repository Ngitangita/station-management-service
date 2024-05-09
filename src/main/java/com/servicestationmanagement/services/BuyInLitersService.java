package com.servicestationmanagement.services;

import com.servicestationmanagement.dtos.BuyInLiterRequest;
import java.util.Map;

public interface BuyInLitersService {
     Map<String, String> buyInLiters(BuyInLiterRequest request);
}
