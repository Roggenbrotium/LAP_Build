package com.example.lap.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    private String description;

    private Long calories;

    private Long sugar;

    private float price;

    private String imagePath;

    @OneToMany(mappedBy = "product")
    private Set<BasketProduct> basketProducts;

    @OneToMany(mappedBy = "product")
    private Set<OrderingProduct> orderingProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getSugar() {
        return sugar;
    }

    public void setSugar(Long sugar) {
        this.sugar = sugar;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Set<BasketProduct> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(Set<BasketProduct> basketProducts) {
        this.basketProducts = basketProducts;
    }

    public Set<OrderingProduct> getOrderingProducts() {
        return orderingProducts;
    }

    public void setOrderingProducts(Set<OrderingProduct> orderingProducts) {
        this.orderingProducts = orderingProducts;
    }
}
