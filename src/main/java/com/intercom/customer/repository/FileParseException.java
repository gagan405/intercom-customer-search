package com.intercom.customer.repository;

public class FileParseException extends RuntimeException {

    public FileParseException(String msg, Throwable t) {
        super(msg, t);
    }
}
