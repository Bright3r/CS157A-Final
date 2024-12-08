package com.ecommerce.repository;

import com.ecommerce.model.UserAddress;
import java.util.List;

public interface UserAddressRepository {
    // Custom query methods (if any)
    List<UserAddress> findAll();
    UserAddress findById(Long id);
    int save(UserAddress userAddress);
    int update(UserAddress userAddress);
    int delete(Long id);
}
