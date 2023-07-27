package com.example.lap.dto;

public class WebUserDTO {
    private String email;

    private String telephone;

    private String billingAddress;

    private String deliveryAddress;

    public WebUserDTO() {
    }

    public WebUserDTO(String email, String telephone, String billingAddress, String deliveryAddress) {
        this.email = email;
        this.telephone = telephone;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
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
}
