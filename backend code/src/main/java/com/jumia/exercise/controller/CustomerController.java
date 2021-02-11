package com.jumia.exercise.controller;

import com.jumia.exercise.domain.Country;
import com.jumia.exercise.entity.Customer;
import com.jumia.exercise.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> findAll(@RequestParam(name = "country", required = false) String country,
                                  @RequestParam(name = "valid", required = false) Boolean valid) {
        return customerService.findAll(country, valid);
    }

    @GetMapping("/search")
    public List<Customer> search(@RequestParam(name = "query", required = false) String query) {
        return customerService.search(query);
    }

    @GetMapping("/countries")
    public List<String> findCountries() {
        List<String> values = new ArrayList<>();
        for (Country country : Country.values()) {
            values.add(country.name().toLowerCase());
        }
        return values;
    }

}
