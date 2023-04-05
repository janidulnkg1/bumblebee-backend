package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.UserNotFoundException;
import com.example.bumblebeebackend.repository.UserRepository;
import com.example.bumblebeebackend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
   private UserRepository userRepository;

    @PostMapping("/user_signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
         userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/user_login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userRepository.findByemail(user.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } else {
            return ResponseEntity.ok("Login successful");
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{userid}")
    public String deleteUser(@PathVariable Long userid){
        if(!userRepository.existsById(userid)){
            throw new UserNotFoundException(userid);
        }
        userRepository.deleteById(userid);
        return "User with id "+userid+" has been deleted!";
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
