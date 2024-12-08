package com.ecommerce.model;

public class UserAddress {

    private Integer userID;
    private Integer addressID;
    private User user;  
    private Address address; 
    private String street;
    private String city;

    public UserAddress() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public User getUser() {
        return user;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "userID=" + userID +
                ", addressID=" + addressID +
                ", user=" + user +
                ", address=" + address +
                '}';
    }
}
