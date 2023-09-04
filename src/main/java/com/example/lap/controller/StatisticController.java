package com.example.lap.controller;

import com.example.lap.domain.*;
import com.example.lap.dto.ProductDTO;
import com.example.lap.dto.StatisticsDTO;
import com.example.lap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Api endpoint for statistics interaction
 */
@Controller
@RequestMapping(path="/api/statistics")
public class StatisticController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(path="/product")
    public @ResponseBody StatisticsDTO getProductStatistics() {
        List<Product> rarestProducts = productRepository.findRarestProducts(5);
        List<ProductDTO> rarestProductsDTOs = new ArrayList<>();
        for (Product product: rarestProducts) {
            rarestProductsDTOs.add(productService.mapProductToProductDTO(product));
        }

        List<Product> mostPopularProducts = productRepository.findMostPopularProducts(5);
        List<ProductDTO> mostPopularProductsDTOs = new ArrayList<>();
        for (Product product: mostPopularProducts) {
            mostPopularProductsDTOs.add(productService.mapProductToProductDTO(product));
        }

        List<Product> productsOverTimespan = productRepository.findAllProductsOfTheLastTimespan(28);
        List<ProductDTO> productsOverTimespanDTOs = new ArrayList<>();
        for (Product product: productsOverTimespan) {
            productsOverTimespanDTOs.add(productService.mapProductToProductDTO(product));
        }

        StatisticsDTO statisticsDTO = new StatisticsDTO(rarestProductsDTOs, mostPopularProductsDTOs, productsOverTimespanDTOs);
        return statisticsDTO;
    }
}