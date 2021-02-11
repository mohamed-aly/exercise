package com.jumia.exercise.service;

import com.jumia.exercise.entity.Customer;

import java.util.List;


public interface CustomerService {

    List<Customer> findAll(String countryName, Boolean valid);

    List<Customer> search(String input);

}
