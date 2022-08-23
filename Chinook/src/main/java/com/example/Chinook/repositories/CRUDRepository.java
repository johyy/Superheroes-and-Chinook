package com.example.Chinook.repositories;

import com.example.Chinook.models.Customer;

import java.util.List;

public interface CRUDRepository <T, U>{
    List<T> findAll();
    Customer findById(int id);
    int insert(T object);
    int update(T object);
    int delete(T object);
    int deleteById(U id);
}
