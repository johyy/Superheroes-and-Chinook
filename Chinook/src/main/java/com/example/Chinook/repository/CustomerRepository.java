package com.example.Chinook.repository;

import com.example.Chinook.models.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    List<Customer> getAllCustomers();

    Customer findById(Integer id);

    int insert(Customer object);

    int update(Customer object);

    int delete(Customer object);

    int deleteById(Integer id);

    void test();
}
