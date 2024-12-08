package com.ecommerce.service;

import com.ecommerce.model.UserAddress;
import com.ecommerce.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    // Get all user addresses
    public List<UserAddress> getAllUserAddresses() {
        return userAddressRepository.findAll();
    }

    // Get user address by userID and addressID
    public UserAddress getUserAddressById(Integer userID, Integer addressID) {
        return null;
    }

    // Get all user addresses for a specific user
    public List<UserAddress> getUserAddressesByUserId(Integer userID) {
    	return null;
    }

    // Add a new user address
    public void addUserAddress(UserAddress userAddress) {
    	
    }

    // Update user address
    public void updateUserAddress(UserAddress userAddress) {
    	
    }

    // Delete user address
    public void deleteUserAddress(Integer userID, Integer addressID) {
     
    }
}
