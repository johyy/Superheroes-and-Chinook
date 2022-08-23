package com.example.Chinook.repositories.Customer;

import com.example.Chinook.models.Customer;
import com.example.Chinook.repositories.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends CRUDRepository {

    int insert(Customer customer);

    int update(Integer id, String phone, String email);

    void test();
    List<Customer> findAll();
}
