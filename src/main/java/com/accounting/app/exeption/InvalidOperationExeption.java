package com.accounting.app.exeption;

public class InvalidOperationExeption extends RuntimeException {
    public InvalidOperationExeption(String message) {
        super(message);
    }
}
