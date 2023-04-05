package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.OrderNotFoundException;


import com.example.bumblebeebackend.exception.UserNotFoundException;
import com.example.bumblebeebackend.repository.CustomerOrderRepository;
import com.example.bumblebeebackend.model.customerOrder;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class orderController {

    @Autowired
    public CustomerOrderRepository customerOrderRepository;

    @PostMapping("/addorder")
    public ResponseEntity<String> addOrder(@RequestBody customerOrder customerorder){
        customerOrderRepository.save(customerorder);
        return ResponseEntity.ok("Order has been added successfully!");
    }

    @GetMapping("/orders")
    public List<customerOrder> getAllOrders() {

        return customerOrderRepository.findAll();
    }

    @DeleteMapping("/orders/{orderid}")
    public String deleteOrder(@PathVariable Long orderid){
        if(!customerOrderRepository.existsById(orderid)){
            throw new OrderNotFoundException(orderid);
        }
        customerOrderRepository.deleteById(orderid);
        return "Order with id "+orderid+" has been deleted!";
    }

    @PutMapping("/order/{orderid}")
    public customerOrder updateOrder(@RequestBody customerOrder newcustomerOrder, @PathVariable Long orderid){
        return customerOrderRepository.findById(orderid)
                .map(order -> {
                    order.setPaymentStatus(newcustomerOrder.getPaymentStatus());
                    return  customerOrderRepository.save(order);
                }).orElseThrow(()->new OrderNotFoundException(orderid));

    }
}
