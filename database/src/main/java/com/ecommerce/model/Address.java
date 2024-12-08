package com.ecommerce.model;

public class Address {
    private int addrID;
    private String country;
    private String state;
    private String city;
    private String street;
    private String houseNumber;
    private String zipcode;
    private int userID;

    // Default constructor
    public Address() {}

    // Getters and Setters
    public int getAddrID() {
        return addrID;
    }

    public void setAddrID(int addrID) {
        this.addrID = addrID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
