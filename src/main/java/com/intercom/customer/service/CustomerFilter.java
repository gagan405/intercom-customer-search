package com.intercom.customer.service;

import com.intercom.customer.model.Customer;

@FunctionalInterface
public interface CustomerFilter {
    boolean filter(Customer customer);
}
