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
        return address;
    }

    // Method to fetch all Addresses
    public List<Address> findAll() {
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

    // Method to save an Address
    public Optional<Address> saveAddress(Address address) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
        String sql = "INSERT INTO Addresses (country, state, city, street, houseNumber, zipcode) "
        			+ "VALUES (?, ?, ?, ?, ?, ?) RETURNING addrID";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, address.getCountry());
    		pstmt.setString(2, address.getState());
    		pstmt.setString(3, address.getCity());
    		pstmt.setString(4, address.getStreet());
    		pstmt.setString(5, address.getHouseNumber());
    		pstmt.setString(6, address.getZipcode());
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {
    			// Return the added address if it exists
    			int addrID = rs.getInt("addrID");
    			return getAddressById(addrID);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	// Return empty if we failed to add the address
    	return Optional.empty();
    }

    // Method to update an Address
    public int updateAddress(Address address) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	String sql = "UPDATE Addresses SET country = ?, state = ?, city = ?, street = ?, "
    				+ "houseNumber = ?, zipcode = ? WHERE addrID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, address.getCountry());
    		pstmt.setString(2, address.getState());
    		pstmt.setString(3, address.getCity());
    		pstmt.setString(4, address.getStreet());
    		pstmt.setString(5, address.getHouseNumber());
    		pstmt.setString(6, address.getZipcode());
    		pstmt.setInt(7, address.getAddrID());
    		
    		// Execute Query
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return 0;
    }

    // Method to delete an Address
    public int deleteAddress(int addrID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
        String sql = "DELETE FROM Addresses WHERE addrID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, addrID);
    		
    		// Execute Query
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return 0;
    }
}
