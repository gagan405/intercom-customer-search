package com.intercom.customer.service;

import java.util.ArrayList;
import java.util.List;

import com.intercom.customer.dto.CustomerSearchResponse;
import com.intercom.customer.dto.Sort;
import com.intercom.customer.dto.Sort.SortField;
import com.intercom.customer.dto.Sort.SortOrder;
import com.intercom.customer.model.Customer;
import com.intercom.customer.repository.CustomerRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerRepository customerRepository = mock(CustomerRepository.class);

    private CustomerService customerService = new CustomerService(customerRepository);

    @Test
    public void testCustomerSearch() {
        List<Customer> customers = getCustomers();
        when(customerRepository.getAllCustomers()).thenReturn(customers);

        List<CustomerSearchResponse> searched = customerService.findCustomers(
            customer -> customer.getName().startsWith("A"),
            new Sort(SortField.USER_ID, SortOrder.DESC)
        );

        Assertions.assertThat(searched)
            .containsExactly(
                new CustomerSearchResponse(2, "Alex"),
                new CustomerSearchResponse(1, "Alice")
            );

        verify(customerRepository).getAllCustomers();
    }

    @Test
    public void testCustomerSearchWhenFindsNothingShouldReturnEmptyList() {
        List<Customer> customers = getCustomers();
        when(customerRepository.getAllCustomers()).thenReturn(customers);

        List<CustomerSearchResponse> searched = customerService.findCustomers(
            customer -> customer.getName().startsWith("Z"),
            new Sort(SortField.USER_ID, SortOrder.DESC)
        );

        Assertions.assertThat(searched).isEmpty();
        verify(customerRepository).getAllCustomers();
    }

    private List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setName("Alice");
        c1.setUserId(1);

        Customer c2 = new Customer();
        c2.setName("Alex");
        c2.setUserId(2);

        Customer c3 = new Customer();
        c3.setName("Bob");
        c3.setUserId(3);

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        return customers;
    }
}