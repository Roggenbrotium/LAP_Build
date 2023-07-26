package com.example.lap.service;

import com.example.lap.dao.Product;
import com.example.lap.dto.ProductDTO;

public interface ProductService {
    ProductDTO mapProductToProductDTO(Product product);
}
