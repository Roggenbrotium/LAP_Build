package com.example.lap.controller;

import com.example.lap.domain.Product;
import com.example.lap.domain.ProductRepository;
import com.example.lap.dto.ProductDTO;
import com.example.lap.dto.ProductIdDTO;
import com.example.lap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public @ResponseBody List<ProductDTO> listAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products) {
            productDTOS.add(productService.mapProductToProductDTO(product));
        }

        return productDTOS;
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody ProductDTO getProductById(@PathVariable Long id) {
        return productService.mapProductToProductDTO(productRepository.findProductById(id));
    }

    @PostMapping(path="/many")
    public @ResponseBody List<ProductDTO> getProductsByIds(@RequestBody ProductIdDTO productIdDTO) {
        List<Product> products = productRepository.findProductByIdIn(productIdDTO.getIds());
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products) {
            productDTOS.add(productService.mapProductToProductDTO(product));
        }

        return productDTOS;
    }
}