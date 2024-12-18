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
	
    // Get all addresses in database
    public List<Address> getAllAddresses() {
    	return addressRepository.findAll();
    }

    // Get address with addrID
    public Optional<Address> getAddressById(Integer addrID) {
    	return addressRepository.getAddressById(addrID);
    }

    // Create a new address tuple in database
    public Optional<Address> addAddress(Address address) {
    	return addressRepository.saveAddress(address);
    }

    // Update attributes of address tuple
    public int updateAddress(Address address) {
    	return addressRepository.updateAddress(address);
    }

    // Delete address tuple from database
    public int deleteAddress(Integer addressID) {
       return addressRepository.deleteAddress(addressID);
    }
    
}
