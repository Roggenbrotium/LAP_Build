package com.example.lap.domain;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * This type of class is required if a table has multiple primary keys
 */
@Embeddable
public class BasketProductKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "basket_id")
    private Long basketId;

    public BasketProductKey() {
    }

    public BasketProductKey(Long productId, Long basketId) {
        this.productId = productId;
        this.basketId = basketId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }
}
