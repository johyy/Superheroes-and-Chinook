package com.example.Chinook.repositories.Customer;

import com.example.Chinook.models.Customer;
import com.example.Chinook.repositories.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends CRUDRepository {


    void test();
    List<Customer> findAll();
    Customer findById(int id);
    Customer findByName(String name);
}
