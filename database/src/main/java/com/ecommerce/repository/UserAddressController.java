package com.ecommerce.repository;

import com.ecommerce.model.UserAddress;
import com.ecommerce.repository.UserAddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository  // Add this annotation to mark the class as a Spring bean
public class UserAddressController implements UserAddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class UserAddressRowMapper implements RowMapper<UserAddress> {
        @Override
        public UserAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserAddress userAddress = new UserAddress();
            userAddress.setUserID(rs.getLong("id"));
            userAddress.setStreet(rs.getString("street"));
            userAddress.setCity(rs.getString("city"));
            userAddress.setState(rs.getString("state"));
            userAddress.setZipCode(rs.getString("zip_code"));
            userAddress.setUserID(rs.getLong("user_id"));
            return userAddress;
        }
    }

    @Override
    public List<UserAddress> findAll() {
        String sql = "SELECT * FROM user_addresses";
        return jdbcTemplate.query(sql, new UserAddressRowMapper());
    }

    @Override
    public UserAddress findById(Long id) {
        String sql = "SELECT * FROM user_addresses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserAddressRowMapper());
    }

    @Override
    public int save(UserAddress userAddress) {
        String sql = "INSERT INTO user_addresses (street, city, state, zip_code, user_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userAddress.getStreet(), userAddress.getCity(),
                userAddress.getState(), userAddress.getZipCode(), userAddress.getUserId());
    }

    @Override
    public int update(UserAddress userAddress) {
        String sql = "UPDATE user_addresses SET street = ?, city = ?, state = ?, zip_code = ? WHERE id = ?";
        return jdbcTemplate.update(sql, userAddress.getStreet(), userAddress.getCity(),
                userAddress.getState(), userAddress.getZipCode(), userAddress.getId());
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM user_addresses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
