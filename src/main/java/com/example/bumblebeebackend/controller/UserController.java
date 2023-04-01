package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.repository.UserRepository;
import com.example.bumblebeebackend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController

public class UserController {

    @Autowired
   private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }



}
