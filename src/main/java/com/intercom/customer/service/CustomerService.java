package com.intercom.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import com.intercom.customer.dto.CustomerSearchResponse;
import com.intercom.customer.dto.Sort;
import com.intercom.customer.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerSearchResponse> findCustomers(final CustomerFilter customerFilter, final Sort sort) {
        return customerRepository.getAllCustomers()
            .stream()
            .filter(customerFilter::filter)
            .sorted(sort.getComparator())
            .map(customer -> new CustomerSearchResponse(customer.getUserId(), customer.getName()))
            .collect(Collectors.toList());
    }

}
