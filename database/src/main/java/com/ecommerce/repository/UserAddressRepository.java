package com.ecommerce.repository;

import com.ecommerce.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
	
}
