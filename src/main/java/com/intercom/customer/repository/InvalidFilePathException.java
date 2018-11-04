package com.intercom.customer.repository;

public class InvalidFilePathException extends RuntimeException {

    public InvalidFilePathException(String msg) {
        super(msg);
    }
}
