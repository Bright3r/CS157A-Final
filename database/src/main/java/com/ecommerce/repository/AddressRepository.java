package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Address;

@Repository
public class AddressRepository {

    public Address buildAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setAddrID(rs.getInt("addrID"));
        address.setCountry(rs.getString("country"));
        address.setState(rs.getString("state"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setHouseNumber(rs.getString("houseNumber"));
        address.setZipcode(rs.getString("zipcode"));
//        address.setUserID(rs.getInt("user_id"));
        return address;
    }

    // Method to get an Address by its ID
    public Optional<Address> getAddressById(int addrID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	Address address = null;
    	
    	// Create a prepared statement to query for address by id
    	String query = "SELECT * FROM Addresses WHERE addrID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set addrID field of prepared statement query
    		pstmt.setInt(1, addrID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Only one row since addressID is primary key
    		// So build address from the first row of ResultSet
    		rs.next();
    		address = buildAddress(rs);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the Address if addrID was in database,
    	// Otherwise return null
    	return Optional.ofNullable(address);
    }

//    // Method to save an Address
//    public void saveAddress(Address address) {
//        String sql = "INSERT INTO Addresses (country, state, city, street, houseNumber, zipcode, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql,
//                address.getCountry(),
//                address.getState(),
//                address.getCity(),
//                address.getStreet(),
//                address.getHouseNumber(),
//                address.getZipcode(),
//                address.getUserID());
//    }
//
//    // Method to update an Address
//    public void updateAddress(Address address) {
//        String sql = "UPDATE Addresses SET country = ?, state = ?, city = ?, street = ?, houseNumber = ?, zipcode = ?, user_id = ? WHERE addrID = ?";
//        jdbcTemplate.update(sql,
//                address.getCountry(),
//                address.getState(),
//                address.getCity(),
//                address.getStreet(),
//                address.getHouseNumber(),
//                address.getZipcode(),
//                address.getUserID(),
//                address.getAddrID());
//    }
//
//    // Method to delete an Address
//    public void deleteAddress(int addrID) {
//        String sql = "DELETE FROM Addresses WHERE addrID = ?";
//        jdbcTemplate.update(sql, addrID);
//    }

    // Method to fetch all Addresses
    public List<Address> findAll() {
    	System.out.println("HIT");
       	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<Address> addresses = new ArrayList<>();
		
    	try {
    		// Query for all addresses in database
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Addresses");
			while (rs.next()) {
				// Create a Address object for each row in the ResultSet
				Address curr = buildAddress(rs);
				addresses.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of every address
    	return addresses;
    }
}
