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

import static java.lang.Integer.parseInt;

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
        getAllCustomers();
        System.out.println("");
        System.out.println("Country with the most customers: " + customerRepository.customersPerCountry());
        System.out.println("");
        customersTopGenre();
        System.out.println("");
        updateCustomer();
        System.out.println("");
        System.out.println("Highest spender customer: " + customerRepository.customerSpender());

        System.out.println("");
        int id = parseInt(reader.read("What is the ID number of the customer You want to find?"));
        Customer idCustomer = customerRepository.findById(id);
        System.out.println("Customer's id: " + idCustomer.customer_id() + ". Name: " + idCustomer.first_name() + " " + idCustomer.last_name() + ". Country: " + idCustomer.country() + ". Postal code: " + idCustomer.postal_code() + ". Phone number and e-mail address: " + idCustomer.phone() + ", " + idCustomer.email() + ".");

        System.out.println("");
        String name = reader.read("What is the first name or last name of the customer You want to find?");
        Customer nameCustomer = customerRepository.findByName(name);
        System.out.println("Customer's id: " + nameCustomer.customer_id() + ". Name: " + nameCustomer.first_name() + " " + nameCustomer.last_name() + ". Country: " + nameCustomer.country() + ". Postal code: " + nameCustomer.postal_code() + ". Phone number and e-mail address: " + nameCustomer.phone() + ", " + nameCustomer.email() + ".");

        System.out.println("");
        int limit = parseInt(reader.read("Set the customer limit: "));
        int offset = parseInt(reader.read("Set the customer offset: "));
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
        Customer newCustomer = new Customer(0, first_name, last_name, country, postal_code, phone, email);
        customerRepository.insert(newCustomer);
        Customer findNewCustomer = customerRepository.findByName(newCustomer.first_name());
        System.out.println("Added a new customer!");
        System.out.println("Customer's id: " + findNewCustomer.customer_id() + ". Name: " + findNewCustomer.first_name() + " " + findNewCustomer.last_name() + ". Country: " + findNewCustomer.country() + ". Postal code: " + findNewCustomer.postal_code() + ". Phone number and e-mail address: " + findNewCustomer.phone() + ", " + findNewCustomer.email() + ".");

        removeCustomer();

        System.out.println("");
        getAllCustomers();
    }

    private void getAllCustomers() {
        /**
         * Print all customers neatly.
         */
        List<Customer> newCustomerList = customerRepository.findAll();
        System.out.println("All customers:");
        for (Customer customer : newCustomerList) {
            System.out.println("Customer's id: " + customer.customer_id() + ". Name: " + customer.first_name() + " " + customer.last_name() + ". Country: " + customer.country() + ". Postal code: " + customer.postal_code() + ". Phone number and e-mail address: " + customer.phone() + ", " + customer.email() + ".");
        }
    }

    private void customersTopGenre(){
        Reader reader = new Reader();
        int uid = parseInt(reader.read("Select the customer id whose most listened genre you want to see: "));
        System.out.println(customerRepository.customerGenre(uid));
    }
    private  void updateCustomer(){
        Reader reader = new Reader();
        int uid = parseInt(reader.read("Select id which customer you want to update :"));
        String num = reader.read("Update customer["+uid+"] phone number: ");
        String name = reader.read("Update customer email: ");
        customerRepository.update(uid,num,name);
    }
    private  void removeCustomer(){
        Reader reader = new Reader();
        int uid = parseInt(reader.read("Which customer will be deleted, give an customer id: "));
        System.out.println(customerRepository.findById(uid));
        customerRepository.deleteById(uid);
        System.out.println("Customer with id " + uid + " will be deleted");
    }

}

