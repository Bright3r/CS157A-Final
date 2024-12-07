package com.ecommerce.controller;

import com.ecommerce.model.Address;
import com.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
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

    // Update an existing address
    @PutMapping("/{addrID}")
    public Address updateAddress(@PathVariable Integer addrID, @RequestBody Address updatedAddress) {
        return addressService.updateAddress(addrID, updatedAddress);
    }

    // Delete an address
    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Integer addressId) {
        addressService.deleteAddress(addressId);
    }
}
