package com.enigma.dynamodbcrud.service;

import com.enigma.dynamodbcrud.entitties.Customer;
import org.springframework.stereotype.Service;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomerById(String customerId);

    String deleteCustomerById(String customerId);

    String updateCustomer(String customerId, Customer customer);
}
