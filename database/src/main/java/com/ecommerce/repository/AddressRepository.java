package com.ecommerce.repository;

import com.ecommerce.model.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	 List<Address> findByUserId(Integer userId);
}
