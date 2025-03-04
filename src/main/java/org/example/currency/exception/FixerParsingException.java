package org.example.currency.exception;

public class FixerParsingException extends RuntimeException {
    public FixerParsingException(String message) {
        super(message);
    }
}
