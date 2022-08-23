package com.example.Chinook.repositories.Customer;

import com.example.Chinook.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Customer> findAll() {
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
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public int insert(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?)";
        int rowsAffect = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer.first_name());
            statement.setString(2, customer.last_name());
            statement.setString(3, customer.country());
            statement.setString(4, customer.postal_code());
            statement.setString(5, customer.phone());
            statement.setString(6, customer.email());
            // Execute statement
            rowsAffect = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffect;
    }


@Override
public int update(Integer id, String phone, String email) {

    String sql = "UPDATE customer SET phone = ?, email = ? WHERE customer_id= ?";
    int rowsAffect = 0;
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        if (!id.equals(id))
            System.out.println("Cannot update database with wrong id");
        // Write statement
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, phone);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, id);
        rowsAffect = preparedStatement.executeUpdate();

    }catch (SQLException e) {
        e.printStackTrace();
    }
    return rowsAffect;
}



    @Override
    public Object findById(Object id) {
        return null;
    }

    @Override
    public int insert(Object object) {
        return 0;
    }

    @Override
    public int update(Object object) {
        return 0;
    }

    @Override
    public int delete(Object object) {
        return 0;
    }

    @Override
    public int deleteById(Object id) {
        return 0;
    }

    public void test() {
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
