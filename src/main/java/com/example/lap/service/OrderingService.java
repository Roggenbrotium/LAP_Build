package com.example.lap.service;

import com.example.lap.domain.Ordering;
import com.example.lap.dto.OrderingDTO;

public interface OrderingService {
    Ordering mapOrderingDTOToOrdering(OrderingDTO orderingDTO);
}
