package com.example.lap.service;

import com.example.lap.dao.Ordering;
import com.example.lap.dao.WebUser;
import com.example.lap.dto.OrderingDTO;
import com.example.lap.dto.RegisterWebUserDTO;
import com.example.lap.dto.WebUserDTO;

public interface OrderingService {
    Ordering mapOrderingDTOToOrdering(OrderingDTO orderingDTO);
}
