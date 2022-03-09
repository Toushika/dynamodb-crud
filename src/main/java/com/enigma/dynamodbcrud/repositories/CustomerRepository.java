package com.enigma.dynamodbcrud.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.enigma.dynamodbcrud.entitties.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public Customer saveCustomer(Customer customer) {
        dynamoDBMapper.save(customer);
        return customer;
    }

    public Customer getCustomerById(String customerId) {
        return dynamoDBMapper.load(Customer.class, customerId);
    }

    public String deleteCustomerById(String customerId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Customer.class, customerId));
        return "Customer Id : " + customerId + " Deleted!";
    }

    public Customer updateCustomer(String customerId, Customer customer) {
        Customer finalCustomer = getCustomerById(customerId);
        finalCustomer.setFirstName(customer.getFirstName());
        finalCustomer.setLastName(customer.getLastName());
        finalCustomer.setEmail(customer.getEmail());
        finalCustomer.setAddress(customer.getAddress());
        dynamoDBMapper.save(finalCustomer);
        return finalCustomer;
    }
}
