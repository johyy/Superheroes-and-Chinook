package com.example.Chinook;

import com.example.Chinook.dao.ChinookDao;
import com.example.Chinook.models.Customer;
import com.example.Chinook.runner.ChinookAppRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChinookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinookApplication.class, args);
	}
}
