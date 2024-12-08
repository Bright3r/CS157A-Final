package com.ecommerce.model;

import java.time.LocalDateTime;

public class Order {

    private Integer orderID;
    private User user;
    private Integer numProductsOrdered;
    private LocalDateTime dateOrdered;
    private Address shippingAddress;

    public Order() {}

    public Order(User user, Integer numProductsOrdered, Address shippingAddress) {
        this.user = user;
        this.numProductsOrdered = numProductsOrdered;
        this.shippingAddress = shippingAddress;
        this.dateOrdered = LocalDateTime.now();
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumProductsOrdered() {
        return numProductsOrdered;
    }

    public void setNumProductsOrdered(Integer numProductsOrdered) {
        this.numProductsOrdered = numProductsOrdered;
    }

    public LocalDateTime getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(LocalDateTime dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
