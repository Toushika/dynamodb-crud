package com.enigma.dynamodbcrud.service;

import com.enigma.dynamodbcrud.entitties.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomerById(String customerId);

    String deleteCustomerById(String customerId);

    Customer updateCustomer(String customerId, Customer customer);
}
