package com.example.Chinook;

import com.example.Chinook.dao.ChinookDao;
import com.example.Chinook.models.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChinookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinookApplication.class, args);
		ChinookDao chinookDao = new ChinookDao();
		System.out.println("RUN FOREST, RUN!!!");
		List<Customer> customerList = chinookDao.findAll();
		customerList.forEach(System.out::println);
	}

}
