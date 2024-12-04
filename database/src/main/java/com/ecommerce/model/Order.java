package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;
import javax.persistence.Table;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private Integer numProductsOrdered;
    private Date dateOrdered;

    @ManyToOne
    @JoinColumn(name = "shippingAddressID")
    private Address shippingAddress;

    // Getters and setters
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
