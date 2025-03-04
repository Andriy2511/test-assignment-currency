package org.example.currency.exception;

public class CategoryCycleDependencyException extends RuntimeException {
    public CategoryCycleDependencyException(String message) {
        super(message);
    }
}
