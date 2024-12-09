package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Address;
import com.ecommerce.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    // Get all addresses
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }
    
    // Get address by addrID
    @GetMapping("/{addrID}")
    public Address getAddressByID(@PathVariable Integer addrID) {
    	return addressService.getAddressById(addrID).orElse(null);
    }
    
    // Add a new address
    @PostMapping
    public int addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    // Update an existing address
    @PutMapping
    public int updateAddress(@RequestBody Address updatedAddress) {
        return addressService.updateAddress(updatedAddress);
    }

    // Delete an address
    @DeleteMapping("/{addressID}")
    public void deleteAddress(@PathVariable Integer addressID) {
        addressService.deleteAddress(addressID);
    }
}
