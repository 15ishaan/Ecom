package com.vanshika.ecom.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private String cart;

    private String cartProdQty;

    private double billingAmt;

    public OrderHistory(User user, String cart, String cartProdQty, double billingAmt) {
        createdDate = new Date();
        this.user = user;
        this.cart = cart;
        this.cartProdQty = cartProdQty;
        this.billingAmt = billingAmt;
    }

    public OrderHistory() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getCartProdQty() {
        return cartProdQty;
    }

    public void setCartProdQty(String cartProdQty) {
        this.cartProdQty = cartProdQty;
    }

    public double getBillingAmt() {
        return billingAmt;
    }

    public void setBillingAmt(double billingAmt) {
        this.billingAmt = billingAmt;
    }
}
