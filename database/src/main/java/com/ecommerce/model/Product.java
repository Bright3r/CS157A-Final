package com.ecommerce.model;

import java.sql.Date;

public class Product {

    private Integer productID;
    private String productName;
    private String brand;
    private Double price;
    private Integer quantity;
    private Date listingDate;
    private String imageUrl;
    private Double rating;

    public Product() {
    }

    public Product(String productName, String brand, Double price, Integer quantity, Date listingDate, String imageUrl) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.listingDate = listingDate;
        this.imageUrl = imageUrl;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", listingDate=" + listingDate +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
