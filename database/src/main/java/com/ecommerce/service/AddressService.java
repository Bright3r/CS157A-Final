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
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Integer addressId) {
        return addressRepository.findById(addressId);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId) {
        addressRepository.deleteById(addressId);
    }
    public List<Address> getAddressesByUserId(Integer userId) {
        return addressRepository.findByUserId(userId); 
    }
    public Address addAddress(Integer userId, Address address) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        address.setUser(user);

        return addressRepository.save(address);
    }
    public Address updateAddress(Integer addrID, Address updatedAddress) {
        Address address = addressRepository.findById(addrID).orElse(null);

        if (address != null) {
            address.setCountry(updatedAddress.getCountry());
            address.setState(updatedAddress.getState());
            address.setCity(updatedAddress.getCity());
            address.setStreet(updatedAddress.getStreet());
            address.setHouseNumber(updatedAddress.getHouseNumber());
            address.setZipcode(updatedAddress.getZipcode());

            return addressRepository.save(address);
        } else {
            return null;
        }
    }
}
