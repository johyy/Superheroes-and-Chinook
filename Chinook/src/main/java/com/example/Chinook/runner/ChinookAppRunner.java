package com.example.Chinook.runner;

import com.example.Chinook.dao.ChinookDao;
import com.example.Chinook.models.Customer;
import com.example.Chinook.reader.Reader;
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
        Reader reader = new Reader();

        List<Customer> customerList = customerRepository.findAll();
        System.out.println("All customers:");
        for (Customer customer : customerList) {
            System.out.println("Customer's id: " + customer.customer_id() + ". Name: " + customer.first_name() + " " + customer.last_name() + ". Country: " + customer.country() + ". Postal code: " + customer.postal_code() + ". Phone number and e-mail address: " + customer.phone() + ", " + customer.email() + ".");
        }

        System.out.println("");
        int id = Integer.parseInt(reader.read("What is the ID number of the customer You want to find?"));
        Customer idCustomer = customerRepository.findById(id);
        System.out.println("Customer's id: " + idCustomer.customer_id() + ". Name: " + idCustomer.first_name() + " " + idCustomer.last_name() + ". Country: " + idCustomer.country() + ". Postal code: " + idCustomer.postal_code() + ". Phone number and e-mail address: " + idCustomer.phone() + ", " + idCustomer.email() + ".");

        System.out.println("");
        String name = reader.read("What is the first name or last name of the customer You want to find?");
        Customer nameCustomer = customerRepository.findByName(name);
        System.out.println("Customer's id: " + nameCustomer.customer_id() + ". Name: " + nameCustomer.first_name() + " " + nameCustomer.last_name() + ". Country: " + nameCustomer.country() + ". Postal code: " + nameCustomer.postal_code() + ". Phone number and e-mail address: " + nameCustomer.phone() + ", " + nameCustomer.email() + ".");

        System.out.println("");
        int limit = Integer.parseInt(reader.read("Set the customer limit: "));
        int offset = Integer.parseInt(reader.read("Set the customer offset: "));
        List<Customer> customerPageList = customerRepository.findAPageOfCustomers(limit, offset);
        for (Customer customer : customerPageList) {
            System.out.println("Customer's id: " + customer.customer_id() + ". Name: " + customer.first_name() + " " + customer.last_name() + ". Country: " + customer.country() + ". Postal code: " + customer.postal_code() + ". Phone number and e-mail address: " + customer.phone() + ", " + customer.email() + ".");
        }

        System.out.println("");
        String first_name = reader.read("Set customer's first name: ");
        String last_name = reader.read("Set customer's last name: ");
        String country = reader.read("Set customer's country: ");
        String postal_code = reader.read("Set customer's postal code: ");
        String phone = reader.read("Set customer's phone number: ");
        String email = reader.read("Set customer's e-mail address: ");
        Customer newCustomer = new Customer (0, first_name, last_name, country, postal_code, phone, email);
        customerRepository.insert(newCustomer);
        System.out.println("Added a new customer!");
        System.out.println("Customer's id: " + newCustomer.customer_id() + ". Name: " + newCustomer.first_name() + " " + newCustomer.last_name() + ". Country: " + newCustomer.country() + ". Postal code: " + newCustomer.postal_code() + ". Phone number and e-mail address: " + newCustomer.phone() + ", " + newCustomer.email() + ".");
    }
}

