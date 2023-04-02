package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.OrderNotFoundException;

import com.example.bumblebeebackend.model.Stock;
import com.example.bumblebeebackend.repository.OrderRepository;
import com.example.bumblebeebackend.model.Order;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class orderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/addorder")
    public ResponseEntity<String> apply(@RequestBody Order order){
        orderRepository.save(order);
        return ResponseEntity.ok("Order has been added successfully!");
    }

    @GetMapping("/orders")
    List<Order> getAllOrders() {

        return orderRepository.findAll();
    }
}
