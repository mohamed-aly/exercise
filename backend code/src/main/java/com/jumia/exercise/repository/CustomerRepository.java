package com.jumia.exercise.repository;

import com.jumia.exercise.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByPhoneContaining(String countryCode);

    List<Customer> findAllByPhoneContainingOrNameContainingIgnoreCase(String phone, String name);

}
