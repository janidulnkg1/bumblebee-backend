package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.repository.UserRepository;
import com.example.bumblebeebackend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

    @Autowired
   private UserRepository userRepository;

}
