package com.intercom.customer.dto;

import java.util.Comparator;

import com.intercom.customer.model.Customer;

import lombok.Value;

@Value
public class Sort {

    public enum SortOrder {
        ASC, DESC
    }

    public enum SortField {
        USER_ID, NAME
    }

    private SortField field;
    private SortOrder sortOrder;

    public Comparator<Customer> getComparator() {
        return sortOrder == SortOrder.ASC ? getDefaultOrderComparator() :
            getDefaultOrderComparator().reversed();
    }

    private Comparator<Customer> getDefaultOrderComparator() {
        switch (field) {
            case NAME:
                return Comparator.comparing(Customer::getName);

            case USER_ID:
            default:
                return Comparator.comparing(Customer::getUserId);
        }
    }
}
