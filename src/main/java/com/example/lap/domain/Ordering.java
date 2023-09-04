package com.example.lap.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

/**
 * Called ordering because order is a reserved word in MySQL
 */
@Entity
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String email;

    @Column(length = 30)
    private String telephone;

    @Column(length = 50)
    private String billingAddress;

    @Column(length = 50)
    private String deliveryAddress;

    private PaymentMethod paymentMethod;

    private Date orderingDate;

    @ManyToOne
    private WebUser webUser;

    @OneToMany(mappedBy = "ordering")
    private Set<OrderingProduct> orderingProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderingDate() {
        return orderingDate;
    }

    public void setOrderingDate(Date orderingDate) {
        this.orderingDate = orderingDate;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public WebUser getUser() {
        return webUser;
    }

    public void setUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public Set<OrderingProduct> getOrderingProducts() {
        return orderingProducts;
    }

    public void setOrderingProducts(Set<OrderingProduct> orderingProducts) {
        this.orderingProducts = orderingProducts;
    }
}
