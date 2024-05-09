package com.servicestationmanagement.dtos;

import java.util.List;

public record UpdateStockRequest(
     Integer stationId,
    List<ProductQuantityUpdate> productQuantityUpdates
 ) {}
