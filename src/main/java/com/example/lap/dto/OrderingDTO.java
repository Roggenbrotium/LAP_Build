package com.example.lap.dto;

import com.example.lap.domain.PaymentMethod;

import java.util.Set;

public class OrderingDTO {
    private String email;

    private String telephone;

    private String billingAddress;

    private String deliveryAddress;

    private PaymentMethod paymentMethod;

    private Set<SimpleProductDTO> simpleProducts;

    public OrderingDTO() {
    }

    public OrderingDTO(String email, String telephone, String billingAddress, String deliveryAddress, PaymentMethod paymentMethod, Set<SimpleProductDTO> simpleProducts) {
        this.email = email;
        this.telephone = telephone;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.simpleProducts = simpleProducts;
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

    public Set<SimpleProductDTO> getSimpleProducts() {
        return simpleProducts;
    }

    public void setSimpleProducts(Set<SimpleProductDTO> simpleProducts) {
        this.simpleProducts = simpleProducts;
    }
}
