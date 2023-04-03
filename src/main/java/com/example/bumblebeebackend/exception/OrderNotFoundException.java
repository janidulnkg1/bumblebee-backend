package com.example.bumblebeebackend.exception;

public class OrderNotFoundException extends RuntimeException{

    public  OrderNotFoundException(Long orderid){

        super("Unable to find the order with id:" +orderid);
    }


}
