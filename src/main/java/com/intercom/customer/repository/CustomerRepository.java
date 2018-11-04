package com.intercom.customer.repository;

import java.util.List;

import com.intercom.customer.model.Customer;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
}
