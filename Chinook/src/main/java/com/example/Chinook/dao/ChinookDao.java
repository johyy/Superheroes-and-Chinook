package com.example.Chinook.dao;

import com.example.Chinook.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChinookDao {

    private String url = "jdbc:postgresql://localhost:5432/chinook";
    private String username = "postgres";
    private String password = "postgres";

    public void ChinookDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getInt("postal_code"),
                        result.getInt("phone_number"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    public void test() {
        try(Connection conn = DriverManager.getConnection(url, username,password);) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
