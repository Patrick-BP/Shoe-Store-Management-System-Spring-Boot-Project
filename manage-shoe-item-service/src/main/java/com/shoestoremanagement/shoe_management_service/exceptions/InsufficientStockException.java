package com.shoestoremanagement.shoe_management_service.exceptions;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(String message){
        super(message);
    }
}
