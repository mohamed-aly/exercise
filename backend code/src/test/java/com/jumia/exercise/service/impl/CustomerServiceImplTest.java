package com.jumia.exercise.service.impl;

import com.jumia.exercise.entity.Customer;
import com.jumia.exercise.exception.CustomException;
import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
class CustomerServiceImplTest {

    @TestConfiguration
    static class Configuration {

        @MockBean
        private static CustomerRepository customerRepository;

        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl(customerRepository);
        }
    }

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        Customer customer1 = Customer.builder().countryName("morocco").id(1).name("Mohamed").phone("(20) 1014162055").valid(true).build();
        Customer customer2 = Customer.builder().countryName("uganda").id(2).name("Ahmed").phone("(273) 112233445566").valid(false).build();


        Mockito.when(Configuration.customerRepository.findAllByPhoneContaining(any(String.class)))
                .thenReturn(Collections.singletonList(customer1));

        Mockito.when(Configuration.customerRepository.findAll())
                .thenReturn(Arrays.asList(customer1, customer2));
    }

    @Test
    void findByCountryNameAndState() {
        List<Customer> list = customerService.findAll("morocco", true);
        Customer customer = list.get(0);

        assertAll("findByCountryNameAndState test",
                () -> assertEquals(list.size(), 1, "size Failed"),
                () -> assertEquals(customer.isValid(), true, "isValid Failed"),
                () -> assertEquals(customer.getPhone(), "(20) 1014162055", "getPhone Failed"),
                () -> assertEquals(customer.getCountryName(), "morocco", "getCountryName Failed"),
                () -> assertEquals(customer.getName(), "Mohamed", "getName Failed")
        );

    }

    @Test
    void findByCountryName() {
        List<Customer> list = customerService.findAll("morocco", null);
        Customer customer = list.get(0);

        assertAll("findByCountryName test",
                () -> assertEquals(list.size(), 1, "size Failed"),
                () -> assertEquals(customer.isValid(), true, "isValid Failed"),
                () -> assertEquals(customer.getPhone(), "(20) 1014162055", "getPhone Failed"),
                () -> assertEquals(customer.getName(), "Mohamed", "getName Failed")
        );

    }

    @Test
    void findAll() {
        List<Customer> list = customerService.findAll(null, null);

        assertAll("findAll test",
                () -> assertEquals(list.size(), 2, "size Failed"),
                () -> assertEquals(list.get(0).isValid(), true, "isValid Failed"),
                () -> assertEquals(list.get(0).getPhone(), "(20) 1014162055", "getPhone Failed"),
                () -> assertEquals(list.get(0).getName(), "Mohamed", "getName Failed"),
                () -> assertEquals(list.get(1).isValid(), false, "isValid Failed"),
                () -> assertEquals(list.get(1).getPhone(), "(273) 112233445566", "getPhone Failed"),
                () -> assertEquals(list.get(1).getName(), "Ahmed", "getName Failed")
        );

    }

    @Test
    void findWithInvalidCountryName() {
        Exception exception = assertThrows(CustomException.class, () -> {
            customerService.findAll("egypt", null);
        });

        String expectedMessage = "No country found with name egypt";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}