package com.example.Chinook.runner;

import com.example.Chinook.repository.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ChinookAppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    public ChinookAppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerRepository.test();
        customerRepository.findAll();
    }


}

