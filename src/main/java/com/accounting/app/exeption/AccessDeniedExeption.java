package com.accounting.app.exeption;

public class AccessDeniedExeption extends RuntimeException {
    public AccessDeniedExeption(String message) {
        super(message);
    }
}
