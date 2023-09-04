package com.example.lap.service;

import com.example.lap.domain.Ordering;
import com.example.lap.dto.OrderingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderingServiceImpl implements OrderingService{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Ordering mapOrderingDTOToOrdering(OrderingDTO orderingDTO) {
        Ordering ordering = modelMapper.map(orderingDTO, Ordering.class);
        return ordering;
    }
}
