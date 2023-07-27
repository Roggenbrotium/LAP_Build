package com.example.lap.dto;

public class RegisterWebUserDTO {
    private String email;

    private String telephone;

    private String billingAddress;

    private String deliveryAddress;

    private String password;

    public RegisterWebUserDTO() {
    }

    public RegisterWebUserDTO(String email, String telephone, String billingAddress, String deliveryAddress, String password) {
        this.email = email;
        this.telephone = telephone;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
