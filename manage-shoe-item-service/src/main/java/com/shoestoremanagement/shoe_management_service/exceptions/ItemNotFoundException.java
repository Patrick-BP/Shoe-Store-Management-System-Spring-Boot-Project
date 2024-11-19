package com.shoestoremanagement.shoe_management_service.exceptions;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(Long id){
        super("Item not found with ID: " + id);
    }
}
