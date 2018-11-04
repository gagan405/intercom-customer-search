package com.intercom.customer.dto;

import lombok.Value;

@Value
public class CustomerSearchResponse {
    private int userId;
    private String name;

    @Override
    public String toString() {
        return name + '\t' + userId;
    }
}
