package com.jumia.exercise.service.impl;

import com.jumia.exercise.domain.Country;
import com.jumia.exercise.entity.Customer;
import com.jumia.exercise.exception.CustomException;
import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll(String countryName, Boolean valid) {

        Country country;

        List<Customer> customers;

        if (countryName != null) {

            try {
                country = Country.valueOf(countryName.toUpperCase());
            } catch (Exception e) {
                throw new CustomException("No country found with name " + countryName);
            }

            customers = customerRepository.findAllByPhoneContaining(country.getCode());

        } else {
            customers = customerRepository.findAll();
        }

        if (valid != null) {
            return customers.stream().filter(c -> c.isValid() == valid).collect(Collectors.toList());
        }

        return customers;
    }

    @Override
    public List<Customer> search(String input) {
        return customerRepository.findAllByPhoneContainingOrNameContainingIgnoreCase(input, input);
    }

}
