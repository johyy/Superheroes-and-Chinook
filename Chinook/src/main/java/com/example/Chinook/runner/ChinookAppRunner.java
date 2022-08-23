package com.example.Chinook.runner;

import com.example.Chinook.dao.ChinookDao;
import com.example.Chinook.models.Customer;
import com.example.Chinook.repositories.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChinookAppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public ChinookAppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Customer> customerList = customerRepository.findAll();
        customerList.forEach(System.out::println);
        System.out.println("");
        System.out.println(customerRepository.findById(5));
        System.out.println("");
        System.out.println(customerRepository.findByName("Emma"));
        System.out.println("");
        List<Customer> customerPageList = customerRepository.findAPageOfCustomers(5, 3);
        customerPageList.forEach(System.out::println);
        System.out.println("");
        Customer sanna = new Customer (100, "Sanna", "Marin", "Finland", "00010", "04012323423", "sanna.marin@eduskunta.fi");
        customerRepository.insert(sanna);
        System.out.println(customerRepository.findByName("Marin"));
    }
}

