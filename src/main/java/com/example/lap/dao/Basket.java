package com.example.lap.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "basket")
    private Set<BasketProduct> basketProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BasketProduct> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(Set<BasketProduct> basketProducts) {
        this.basketProducts = basketProducts;
    }
}
