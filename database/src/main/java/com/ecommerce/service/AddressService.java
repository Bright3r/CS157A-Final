package com.ecommerce.service;

import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.repository.AddressRepository;
import com.ecommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<Address> getAllAddresses() {
    	return null;
    }

    public Optional<Address> getAddressById(Integer addressId) {
    	return null;
    }

    public Address addAddress(Address address) {
    	return null;
    }

    public Address updateAddress(Address address) {
    	return null;
    }

    public void deleteAddress(Integer addressId) {
       
    }
    public List<Address> getAddressesByUserId(Integer userId) {
    	return null;
    }
    public Address addAddress(Integer userId, Address address) {
    	return null;
    }
}
