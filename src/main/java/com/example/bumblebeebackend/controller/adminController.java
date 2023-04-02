package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.AdminNotFoundException;
import com.example.bumblebeebackend.repository.AdminRepository;
import com.example.bumblebeebackend.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class adminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/admin_signup")
    public ResponseEntity<String> signup(@RequestBody Admin admin) {
        adminRepository.save(admin);
        return ResponseEntity.ok("ADMIN ACCOUNT created successfully");
    }

    @PostMapping("/admin_login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        Admin existingAdmin = adminRepository.findByemail(admin.getEmail());
        if (existingAdmin == null || !existingAdmin.getPassword().equals(admin.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } else {
            return ResponseEntity.ok("Login successful");
        }
    }

    @GetMapping("/admins")
    List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    @DeleteMapping("/admin/{id}")
    String deleteAdmin(@PathVariable Long id){
        if(!adminRepository.existsById(id)){
            throw new AdminNotFoundException(id);
        }
        adminRepository.deleteById(id);
        return "Admin with id "+id+" has been deleted!";
    }

}
