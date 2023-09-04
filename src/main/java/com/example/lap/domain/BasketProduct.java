package com.example.lap.domain;

import jakarta.persistence.*;

/**
 * A table between a many-to-many relationship is only necessary if it has additional columns
 */
@Entity
public class BasketProduct {
    @EmbeddedId
    private BasketProductKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("basketId")
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private int amount;

    public BasketProduct() {
    }

    public BasketProduct(Product product, Basket basket, int amount) {
        this.product = product;
        this.basket = basket;
        this.amount = amount;
        this.id = new BasketProductKey(product.getId(), basket.getId());
    }

    public BasketProductKey getId() {
        return id;
    }

    public void setId(BasketProductKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
