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

    // Get a list of addresses for a user
    @GetMapping("/users/{userId}/addresses")
    public List<Address> getAddressesByUserId(@PathVariable Integer userId) {
        return addressService.getAddressesByUserId(userId);
    }

    // Add a new address for a user
    @PostMapping("/users/{userId}/addresses")
    public Address addAddress(@PathVariable Integer userId, @RequestBody Address address) {
        return addressService.addAddress(userId, address);
    }
    
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

    // Update an existing address
    @PutMapping("/{addrID}")
    public Address updateAddress(@PathVariable Integer addrID, @RequestBody Address updatedAddress) {
        return null;
    }

    // Delete an address
    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Integer addressId) {
        addressService.deleteAddress(addressId);
    }
}
