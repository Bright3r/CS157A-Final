package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Address;
import com.ecommerce.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
	
    public List<Address> getAllAddresses() {
    	return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Integer addrID) {
    	return addressRepository.getAddressById(addrID);
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
