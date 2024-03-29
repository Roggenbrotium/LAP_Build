package com.example.lap.domain;

import jakarta.persistence.*;

/**
 * A table between a many-to-many relationship is only necessary if it has additional columns
 */
@Entity
public class OrderingProduct {
    @EmbeddedId
    private OrderingProductKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("orderingId")
    @JoinColumn(name = "ordering_id")
    private Ordering ordering;

    private int amount;

    public OrderingProduct() {
    }

    public OrderingProduct(Product product, Ordering ordering, int amount) {
        this.product = product;
        this.ordering = ordering;
        this.amount = amount;
        this.id = new OrderingProductKey(product.getId(), ordering.getId());
    }

    public OrderingProductKey getId() {
        return id;
    }

    public void setId(OrderingProductKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
