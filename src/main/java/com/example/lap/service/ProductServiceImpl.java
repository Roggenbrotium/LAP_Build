package com.example.lap.service;

import com.example.lap.dao.Product;
import com.example.lap.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO mapProductToProductDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }
}
