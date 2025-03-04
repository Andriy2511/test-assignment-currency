package org.example.currency.errorhandler;

import org.example.currency.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    private StringBuilder errorDetailsBuilder;

    @ExceptionHandler(RoleNotFoundException.class)
    public String handleEntityNotFoundException(RoleNotFoundException e) {
        errorDetailsBuilder = new StringBuilder();
        return buildStringWithErrorDetailsAndStatus(errorDetailsBuilder, HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        errorDetailsBuilder = new StringBuilder();
        return buildStringWithErrorDetailsAndStatus(errorDetailsBuilder, HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleEntityNotFoundException(NoSuchElementException e) {
        errorDetailsBuilder = new StringBuilder();
        return buildStringWithErrorDetailsAndStatus(errorDetailsBuilder, HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(CategoryCycleDependencyException.class)
    public String handleCategoryCycleDependencyException(CategoryCycleDependencyException e) {
        errorDetailsBuilder = new StringBuilder();
        return buildStringWithErrorDetailsAndStatus(errorDetailsBuilder, HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public String handleCurrencyNotFoundException(CurrencyNotFoundException e) {
        errorDetailsBuilder = new StringBuilder();
        return buildStringWithErrorDetailsAndStatus(errorDetailsBuilder, HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleCurrencyNotFoundException(ProductNotFoundException e) {
        errorDetailsBuilder = new StringBuilder();
        return buildStringWithErrorDetailsAndStatus(errorDetailsBuilder, HttpStatus.NOT_FOUND, e);
    }

    private String buildStringWithErrorDetailsAndStatus(StringBuilder errorDetails, HttpStatus status, Exception e) {
        errorDetails
                .append(e.getMessage())
                .append(System.lineSeparator())
                .append("Http Status : ")
                .append(status);

        return errorDetails.toString();
    }
}
