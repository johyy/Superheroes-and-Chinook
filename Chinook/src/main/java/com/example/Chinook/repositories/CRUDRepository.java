package com.example.Chinook.repositories;

import com.example.Chinook.models.Customer;
import java.util.List;

public interface CRUDRepository <T, U> {
    List<Customer> findAll();
    Customer findById(int id);
    boolean insert(Customer customer);
    int update(int id , String phone , String email);
    int deleteById(int id);
}
