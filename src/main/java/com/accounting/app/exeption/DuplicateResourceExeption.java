package com.accounting.app.exeption;

public class DuplicateResourceExeption extends RuntimeException {
    public DuplicateResourceExeption(String message) {
        super(message);
    }
}
