package com.intercom.customer.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.intercom.customer.dto.Sort.SortField;
import com.intercom.customer.dto.Sort.SortOrder;
import com.intercom.customer.model.Customer;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SortTest {

    @Test
    public void testNameComparatorAscending() {
        Sort sort = new Sort(SortField.NAME, SortOrder.ASC);

        Comparator<Customer> customerComparator = sort.getComparator();

        Customer c1 = new Customer();
        c1.setName("Alice");

        Customer c2 = new Customer();
        c2.setName("Bob");

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        Collections.sort(customers, customerComparator);

        Assertions.assertThat(customers).containsExactly(c1, c2);
    }

    @Test
    public void testNameComparatorDescending() {
        Sort sort = new Sort(SortField.NAME, SortOrder.DESC);

        Comparator<Customer> customerComparator = sort.getComparator();

        Customer c1 = new Customer();
        c1.setName("Alice");

        Customer c2 = new Customer();
        c2.setName("Bob");

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        Collections.sort(customers, customerComparator);

        Assertions.assertThat(customers).containsExactly(c2, c1);
    }

    @Test
    public void testIdComparatorAscending() {
        Sort sort = new Sort(SortField.USER_ID, SortOrder.ASC);

        Comparator<Customer> customerComparator = sort.getComparator();

        Customer c1 = new Customer();
        c1.setName("Alice");
        c1.setUserId(2);

        Customer c2 = new Customer();
        c2.setName("Bob");
        c2.setUserId(1);

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        Collections.sort(customers, customerComparator);

        Assertions.assertThat(customers).containsExactly(c2, c1);
    }

    @Test
    public void testIdComparatorDescending() {
        Sort sort = new Sort(SortField.USER_ID, SortOrder.DESC);

        Comparator<Customer> customerComparator = sort.getComparator();

        Customer c1 = new Customer();
        c1.setName("Alice");
        c1.setUserId(2);

        Customer c2 = new Customer();
        c2.setName("Bob");
        c2.setUserId(1);

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        Collections.sort(customers, customerComparator);

        Assertions.assertThat(customers).containsExactly(c1, c2);
    }
}