package com.ecommerce.repository;

import com.ecommerce.model.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	 @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
	 List<Address> findByUserId(@Param("userId") Integer userId);
}
