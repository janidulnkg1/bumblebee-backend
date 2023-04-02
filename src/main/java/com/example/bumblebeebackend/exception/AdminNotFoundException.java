package com.example.bumblebeebackend.exception;

public class AdminNotFoundException extends RuntimeException{

    public  AdminNotFoundException(Long id){

        super("Unable to find the admin with id:" +id);
    }
}
