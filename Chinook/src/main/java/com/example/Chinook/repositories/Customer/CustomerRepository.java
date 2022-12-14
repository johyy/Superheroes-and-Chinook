package com.example.Chinook.repositories.Customer;

import com.example.Chinook.models.Customer;
import com.example.Chinook.models.CustomerCountry;
import com.example.Chinook.models.CustomerGenre;
import com.example.Chinook.models.CustomerSpender;
import com.example.Chinook.repositories.CRUDRepository;

import java.util.List;

public interface CustomerRepository extends CRUDRepository {

    List<Customer> findAll();
    Customer findById(int id);
    Customer findByName(String name);
    List<Customer> findAPageOfCustomers(int limit, int offset);
    boolean insert(Customer customer);
    int update(int id, String phone, String email);
    CustomerCountry customersPerCountry();
    CustomerSpender customerSpender();
    List<CustomerGenre> customerGenre(int Customer_id);
    int deleteById(int id);
}
