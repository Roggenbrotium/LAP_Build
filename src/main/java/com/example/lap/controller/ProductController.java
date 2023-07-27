package com.example.lap.controller;

import com.example.lap.dao.Product;
import com.example.lap.dao.ProductRepository;
import com.example.lap.dto.ProductDTO;
import com.example.lap.dto.ProductIdDTO;
import com.example.lap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Api endpoint for product interaction
 */
@Controller
@RequestMapping(path="/api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody Product getProductById(@PathVariable Long id) {
        return productRepository.findProductById(id);
    }

    @PostMapping(path="/many")
    public @ResponseBody Set<ProductDTO> getProductsByIds(@RequestBody ProductIdDTO productIdDTO) {
        Set<Product> products = productRepository.findProductByIdIn(productIdDTO.getIds());
        Set<ProductDTO> productDTOS = new HashSet<>();

        for (Product product: products) {
            productDTOS.add(productService.mapProductToProductDTO(product));
        }

        return productDTOS;
    }
}