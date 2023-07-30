package com.example.lap.dto;

import java.util.List;

public class StatisticsDTO {
    private List<ProductDTO> rarestProducts;

    private List<ProductDTO> mostPopularProducts;

    private List<ProductDTO> productsOverTimespan;

    public StatisticsDTO() {
    }

    public StatisticsDTO(List<ProductDTO> rarestProducts, List<ProductDTO> mostPopularProducts, List<ProductDTO> productsOverTimespan) {
        this.rarestProducts = rarestProducts;
        this.mostPopularProducts = mostPopularProducts;
        this.productsOverTimespan = productsOverTimespan;
    }

    public List<ProductDTO> getRarestProducts() {
        return rarestProducts;
    }

    public void setRarestProducts(List<ProductDTO> rarestProducts) {
        this.rarestProducts = rarestProducts;
    }

    public List<ProductDTO> getMostPopularProducts() {
        return mostPopularProducts;
    }

    public void setMostPopularProducts(List<ProductDTO> mostPopularProducts) {
        this.mostPopularProducts = mostPopularProducts;
    }

    public List<ProductDTO> getProductsOverTimespan() {
        return productsOverTimespan;
    }

    public void setProductsOverTimespan(List<ProductDTO> productsOverTimespan) {
        this.productsOverTimespan = productsOverTimespan;
    }
}
