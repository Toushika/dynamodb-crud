package com.enigma.dynamodbcrud.service;

import com.enigma.dynamodbcrud.entitties.Customer;
import com.enigma.dynamodbcrud.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("CustomerServiceImpl :: saveCustomer :: customer :: {}", customer);
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        log.info("CustomerServiceImpl :: getCustomerById :: customerId :: {}", customerId);
        return customerRepository.getCustomerById(customerId);
    }

    @Override
    public String deleteCustomerById(String customerId) {
        log.info("CustomerServiceImpl :: deleteCustomerById :: customerId :: {}", customerId);
        return customerRepository.deleteCustomerById(customerId);
    }

    @Override
    public Customer updateCustomer(String customerId, Customer customer) {
        log.info("CustomerServiceImpl :: updateCustomer :: customerId :: {} :: customer :: {}", customerId, customer);
        return customerRepository.updateCustomer(customerId, customer);
    }
}
