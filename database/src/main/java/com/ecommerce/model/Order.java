package com.ecommerce.model;

import java.sql.Date;
import java.util.List;

public class Order {

    private Integer orderID;
    private User user;
    private List<CartItem> products;
    private Integer numProductsOrdered;
    private Date dateOrdered;
    private Address shippingAddress;
    private Double totalCost;

    public Order() {}

    public Order(User user, Integer numProductsOrdered, Address shippingAddress, Date dateOrdered, Double totalCost) {
        this.user = user;
        this.numProductsOrdered = numProductsOrdered;
        this.shippingAddress = shippingAddress;
        this.dateOrdered = dateOrdered;
        this.totalCost = totalCost;
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
    
    public List<CartItem> getProducts() {
    	return products;
    }
    
    public void setProducts(List<CartItem> products) {
    	this.products = products;
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
    
    public Double getTotalCost() {
    	return totalCost;
    }
    
    public void setTotalCost(Double totalCost) {
    	this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", user=" + user +
                ", products=" + products +
                ", numProductsOrdered=" + numProductsOrdered +
                ", dateOrdered=" + dateOrdered +
                ", shippingAddress=" + shippingAddress +
                '}';
    }
}
