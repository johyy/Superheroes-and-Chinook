package com.example.Chinook.repositories;

import com.example.Chinook.models.Customer;
import com.example.Chinook.models.CustomerCountry;
import com.example.Chinook.models.CustomerGenre;
import com.example.Chinook.models.CustomerSpender;

import java.util.List;

public interface CRUDRepository <T, U>{
    List<T> findAll();
    T findById(int id);
    T findByName(String name);
    List<T> findAPageOfCustomers(int limit, int offset);
    boolean insert(Customer customer);
    int update(int id , String phone , String email);
    int deleteById(int U);
    CustomerCountry customersPerCountry();
    CustomerSpender customerSpender();
    List<CustomerGenre> customerGenre (int Customer_id);



}
