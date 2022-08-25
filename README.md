# Assignment 2: Data Persistence and Access - Chinook and Superheroes

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
[![Java](https://img.shields.io/badge/-Java-red?logo=java)](https://www.java.com)
[![Spring](https://img.shields.io/badge/-Spring-white?logo=spring)](https://spring.io/)

This is a Spring Boot Application using a PostgreSQL database. This application is the second assignment
of the backend part of the [Noroff](https://www.noroff.no/en/) Full-Stack developer course.  
 
## Table of Contents

- [Background](#background)
- [Install](#install)
- [Maintainers](#maintainers)
- [License](#license)

## Background
This assignment consists of two independent parts:
1. SQL scripts in directory [Superheroes](./Superheroes).
2. Chinook application in directory [Chinook](./Chinook).

### SQL scripts for creating superheroes database
- create tables
- relationships between tables
- populate data

### Data persistence and access to database called chinook

Requirements:
1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code, 
phone number and email.
2. Read a specific customer from the database (by Id), should display everything listed in the above point.
3. Read a specific customer by name. HINT: LIKE keyword can help for partial matches.
4. Return a page of customers from the database. This should take in limit and offset as parameters and make use 
of the SQL limit and offset keywords to get a subset of the customer data. The customer model from above 
should be reused.
5. Add a new customer to the database. You also need to add only the fields listed above (our customer object) 
6. Update an existing customer.
7. Return the country with the most customers.
8. Customer who is the highest spender (total in invoice table is the largest).
9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this context 
means the genre that corresponds to the most tracks from invoices associated to that customer.

## Install

This project was generated with OpenJDK version 17.0.4.1 through Spring Initializr and Gradle build system. 
Clone repository via `git clone`.

## Maintainers

[@Jonna Hyypiä](https://gitlab.com/johyy)
[@Sami Sihvonen](https://gitlab.com/sami_sihvonen)

## License

MIT © 2022 Jonna Hyypiä, Sami Sihvonen
