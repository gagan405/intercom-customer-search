package com.intercom.customer.utils;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String msg) {
        super(msg);
    }
}
