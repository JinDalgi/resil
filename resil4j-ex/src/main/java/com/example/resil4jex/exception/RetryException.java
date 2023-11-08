package com.example.resil4jex.exception;

public class RetryException extends RuntimeException {
    public RetryException(String msg) {
        super(msg);
    }
}
