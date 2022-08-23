package com.example.Chinook;

import com.example.Chinook.dao.ChinookDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChinookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinookApplication.class, args);
		ChinookDao chinookDao = new ChinookDao();

		System.out.println("RUN FOREST, RUN!!!");
	}

}
