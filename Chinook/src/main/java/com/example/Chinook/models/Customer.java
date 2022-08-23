package com.example.Chinook.models;

public record Customer(int customer_id, String first_name, String last_name, String country, int postal_code, int phone_number, String email
) {
}
