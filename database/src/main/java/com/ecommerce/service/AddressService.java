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

    public int addAddress(Address address) {
    	return addressRepository.saveAddress(address);
    }

    public int updateAddress(Address address) {
    	return addressRepository.updateAddress(address);
    }

    public int deleteAddress(Integer addressID) {
       return addressRepository.deleteAddress(addressID);
    }
    
}
