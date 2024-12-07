package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "app_user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"userID\"")
    private Integer userID;
	@Column(name = "\"userName\"")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "addressID")
    private Address address;
    
    
    @Column(name = "\"password\"")
    private String password; 
    @Column(name = "email")
    private String email;
    @Column(name = "\"phoneNumber\"")
    private String phoneNumber;
    
    public User() {}

    // Constructor with parameters
    public User(String userName, String email, String password, Address address, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and setters
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public String getPassword() { // Add the getter for password
        return password;
    }

    public void setPassword(String password) { // Add the setter for password
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
