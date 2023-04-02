package com.example.bumblebeebackend.exception;

public class StockNotFoundException extends RuntimeException{
    public  StockNotFoundException(Long id){

        super("Unable to find the product stock with id:" +id);
    }
}
