package com.ecommerce.repository;

import com.ecommerce.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	Optional<User> findByUserName(@Param("userName") String userName);
}
