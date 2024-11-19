package com.shoestoremanagement.billing_service.exceptions;

public class InvalidBillingDataException extends RuntimeException {
    public InvalidBillingDataException(String message) {
        super(message);
    }
}

