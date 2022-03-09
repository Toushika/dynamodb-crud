package com.enigma.dynamodbcrud.service;

import com.enigma.dynamodbcrud.entitties.Customer;
import com.enigma.dynamodbcrud.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    public CustomerServiceImpl(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    @Override
    public String deleteCustomerById(String customerId) {
        return customerRepository.deleteCustomerById(customerId);
    }

    @Override
    public String updateCustomer(String customerId, Customer customer) {
        return customerRepository.updateCustomer(customerId, customer);
    }
}
