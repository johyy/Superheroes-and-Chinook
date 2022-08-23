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
    int insert(T customer);
    int update(U id , String phone , String email);

    CustomerCountry customersPerCountry();
    CustomerSpender customerSpender();

    List<CustomerGenre> customerGenre (int Customer_id);

    int deleteById(U id);

}
