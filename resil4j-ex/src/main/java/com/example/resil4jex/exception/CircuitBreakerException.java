package com.example.resil4jex.exception;

public class CircuitBreakerException extends RuntimeException {
    public CircuitBreakerException(String msg) {
        super(msg);
    }
}
