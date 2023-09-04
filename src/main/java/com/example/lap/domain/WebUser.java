package com.example.lap.domain;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Called webuser because user is a reserved word in MySQL
 */
@Entity
public class WebUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(length = 30)
    private String telephone;

    @Column(length = 50)
    private String billingAddress;

    @Column(length = 50)
    private String deliveryAddress;

    @Column(nullable = false)
    private String password;

    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;

    @OneToMany(mappedBy = "webUser")
    private Set<Ordering> orderings;

    public WebUser() {
    }

    public WebUser(String email, String telephone, String billingAddress, String deliveryAddress, String password, Basket basket) {
        this.email = email;
        this.telephone = telephone;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.password = password;
        this.basket = basket;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Set<Ordering> getOrderings() {
        return orderings;
    }

    public void setOrderings(Set<Ordering> orderings) {
        this.orderings = orderings;
    }
}
