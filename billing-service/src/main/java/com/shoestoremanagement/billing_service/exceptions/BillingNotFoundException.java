package com.shoestoremanagement.billing_service.exceptions;

public class BillingNotFoundException extends RuntimeException {
    public BillingNotFoundException(String message) {
        super(message);
    }
}

