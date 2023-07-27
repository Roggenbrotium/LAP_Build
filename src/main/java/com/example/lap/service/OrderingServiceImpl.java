package com.example.lap.service;

import com.example.lap.dao.Ordering;
import com.example.lap.dao.PaymentMethod;
import com.example.lap.dao.WebUser;
import com.example.lap.dto.OrderingDTO;
import com.example.lap.dto.RegisterWebUserDTO;
import com.example.lap.dto.WebUserDTO;
import org.modelmapper.Converter;
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
