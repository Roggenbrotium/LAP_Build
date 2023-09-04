package com.example.lap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * This type of class is required if a table has multiple primary keys
 */
@Embeddable
public class OrderingProductKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "ordering_id")
    private Long orderingId;

    public OrderingProductKey() {
    }

    public OrderingProductKey(Long productId, Long orderingId) {
        this.productId = productId;
        this.orderingId = orderingId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(Long orderingId) {
        this.orderingId = orderingId;
    }
}
