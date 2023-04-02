package com.example.bumblebeebackend.exception;

public class LeaseNotFoundException extends RuntimeException{

    public  LeaseNotFoundException(Long id){

        super("Unable to find the lease with id:" +id);
    }
}
