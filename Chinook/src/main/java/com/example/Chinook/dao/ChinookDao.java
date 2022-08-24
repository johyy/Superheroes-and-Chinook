package com.example.Chinook.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChinookDao {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void ChinookDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
