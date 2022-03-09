package com.enigma.dynamodbcrud.controller;

import com.enigma.dynamodbcrud.entitties.Customer;
import com.enigma.dynamodbcrud.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        log.info("CustomerController :: saveCustomer :: Customer :: {}", customer);
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/get/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") String customerId) {
        log.info("CustomerController :: getCustomerById :: customerId :: {}", customerId);
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/delete/customer/{id}")
    public String deleteCustomerById(@PathVariable("id") String customerId) {
        log.info("CustomerController :: deleteCustomerById :: customerId :: {}", customerId);
        return customerService.deleteCustomerById(customerId);
    }

    @PostMapping("/update/customer/{id}")
    public Customer updateCustomer(@PathVariable("id") String customerId, @RequestBody Customer customer) {
        log.info("CustomerController :: updateCustomer :: customerId :: {} :: customer :: {} ", customerId, customer);
        return customerService.updateCustomer(customerId, customer);
    }
}
