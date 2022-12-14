package com.example.Chinook.repositories.Customer;

import com.example.Chinook.models.Customer;
import com.example.Chinook.models.CustomerCountry;
import com.example.Chinook.models.CustomerGenre;
import com.example.Chinook.models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    /**
     * This is a class to implement working methods in CustomerRepository.
     * @param args
     */
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
    public Customer findById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while (result.next()) {
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer findByName(String name) {
        /**
         * Find customer by name.
         * @param name
         * @return Customer
         */

        String sql = "SELECT * FROM customer WHERE first_name LIKE ? OR last_name LIKE ?";
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, name);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while (result.next()) {
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> findAPageOfCustomers(int limit, int offset) {
        /**
         * Find limited amount of customers from specific offset on.
         * @param limit, offset
         * @return List<>
         */
        String sql = "SELECT * FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            // Execute statement
            try (ResultSet result = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean insert(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer.first_name());
            statement.setString(2, customer.last_name());
            statement.setString(3, customer.country());
            statement.setString(4, customer.postal_code());
            statement.setString(5, customer.phone());
            statement.setString(6, customer.email());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int update(int id, String phone, String email) {
        String sql = "UPDATE customer SET  phone = ?, email = ? WHERE customer_id= ?";
        int rowsAffect = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, phone);
            statement.setString(2, email);
            statement.setInt(3, id);
            rowsAffect = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffect;
    }

    public CustomerCountry customersPerCountry() {
        /**
         * Return the country with the most customers.
         * @return customersPerCountry
         */
        CustomerCountry customersPerCountry = null;
        String sql = "SELECT COUNT(customer_id) as total_customers, country FROM customer " +
                "GROUP BY country ORDER BY COUNT(*) DESC LIMIT 1";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            //Process result
            while (result.next()) {

                customersPerCountry= new CustomerCountry(
                        result.getString("country"),
                        result.getInt("total_customers")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersPerCountry;
    }

    public CustomerSpender customerSpender() {
        /**
         * Return customer who is the highest spender (total in invoice table is the largest).
         * @return customersSpender
         */
        CustomerSpender customerSpender = null;
        String sql = "SELECT (customer.customer_id), customer.first_name, customer.last_name, SUM(invoice.total) as total\n" +
                "FROM customer\n" +
                "JOIN invoice ON customer.customer_id = invoice.customer_id GROUP BY invoice.customer_id, customer.customer_id  ORDER by total  desc LIMIT 1";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                customerSpender= new CustomerSpender(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getDouble("total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerSpender;
    }

    public List<CustomerGenre> customerGenre(int Customer_id) {
        /**
         * For a given customer, return their most popular genre.
         * @return customerMostPopGenre
         */
        List<CustomerGenre> customerMostPopGenre = new ArrayList<>();

        String sql = "SELECT genre.name, customer.first_name, COUNT(genre.genre_id) AS top_genre\n" +
                "FROM customer\n" +
                "INNER JOIN invoice ON customer.customer_id = invoice.customer_id\n" +
                "INNER JOIN invoice_line ON invoice_line.invoice_id = invoice.invoice_id\n" +
                "INNER JOIN track ON track.track_id = invoice_line.track_id\n" +
                "INNER JOIN genre ON genre.genre_id = track.genre_id\n" +
                "WHERE customer.customer_id = ?\n" +
                "GROUP BY genre.genre_id, genre.\"name\", customer.first_name\n" +
                "ORDER BY top_genre DESC\n" +
                "LIMIT(1);";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Customer_id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerMostPopGenre.add(
                        new CustomerGenre(
                                result.getString( "name"),
                                result.getString(  "first_name"),
                                result.getInt("top_genre" ))
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customerMostPopGenre;
    }

    @Override
    public int deleteById(int id) {
        alterInvoice();
        alterInvoiceLine();
        String sql = "DELETE FROM customer WHERE customer_id= ?";
        int rowsAffect = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rowsAffect = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffect;
    }

    public void alterInvoice() {
        /**
         * Alter table invoice so that customer can be deleted.
         */
        String sql = "ALTER TABLE invoice DROP CONSTRAINT fk_invoice_customer_id, ADD CONSTRAINT fk_invoice_customer_id FOREIGN KEY (customer_id) REFERENCES customer ON DELETE CASCADE";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterInvoiceLine () {
        /**
         * Alter table invoice_line so that invoice can be deleted and by that a customer can be deleted.
         */
        String sql = "ALTER TABLE invoice_line DROP CONSTRAINT fk_invoice_line_invoice_id, ADD CONSTRAINT fk_invoice_line_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoice ON DELETE CASCADE";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
