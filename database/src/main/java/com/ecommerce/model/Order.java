package com.ecommerce.model;

import java.sql.Date;

public class Order {

    private Integer orderID;
    private User user;
    private Integer numProductsOrdered;
    private Date dateOrdered;
    private Address shippingAddress;

    public Order() {}

    public Order(User user, Integer numProductsOrdered, Address shippingAddress) {
        this.user = user;
        this.numProductsOrdered = numProductsOrdered;
        this.shippingAddress = shippingAddress;
        this.dateOrdered = new Date(1);
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

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
