package com.example.bumblebeebackend.exception;

public class OrderNotFoundException extends RuntimeException{

    public  OrderNotFoundException(Long id){

        super("Unable to find the lease with id:" +id);
    }
}
