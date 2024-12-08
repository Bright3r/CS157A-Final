package com.ecommerce.repository;

import com.ecommerce.model.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to get an Address by its ID
    public Address getAddressById(int addrID) {
        String sql = "SELECT * FROM Addresses WHERE addrID = ?";
        return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> {
            Address address = new Address();
            address.setAddrID(rs.getInt("addrID"));
            address.setCountry(rs.getString("country"));
            address.setState(rs.getString("state"));
            address.setCity(rs.getString("city"));
            address.setStreet(rs.getString("street"));
            address.setHouseNumber(rs.getString("houseNumber"));
            address.setZipcode(rs.getString("zipcode"));
            address.setUserID(rs.getInt("user_id")); // Mapping the userID foreign key
            return address;
        }, addrID);
    }

    // Method to save an Address
    public void saveAddress(Address address) {
        String sql = "INSERT INTO Addresses (country, state, city, street, houseNumber, zipcode, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getZipcode(),
                address.getUserID());
    }

    // Method to update an Address
    public void updateAddress(Address address) {
        String sql = "UPDATE Addresses SET country = ?, state = ?, city = ?, street = ?, houseNumber = ?, zipcode = ?, user_id = ? WHERE addrID = ?";
        jdbcTemplate.update(sql,
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getZipcode(),
                address.getUserID(),
                address.getAddrID());
    }

    // Method to delete an Address
    public void deleteAddress(int addrID) {
        String sql = "DELETE FROM Addresses WHERE addrID = ?";
        jdbcTemplate.update(sql, addrID);
    }

    // Method to fetch all Addresses (optional)
    public List<Address> getAllAddresses() {
        String sql = "SELECT * FROM Addresses";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Address address = new Address();
            address.setAddrID(rs.getInt("addrID"));
            address.setCountry(rs.getString("country"));
            address.setState(rs.getString("state"));
            address.setCity(rs.getString("city"));
            address.setStreet(rs.getString("street"));
            address.setHouseNumber(rs.getString("houseNumber"));
            address.setZipcode(rs.getString("zipcode"));
            address.setUserID(rs.getInt("user_id"));
            return address;
        });
    }
}
