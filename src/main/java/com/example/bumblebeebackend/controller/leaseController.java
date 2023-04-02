package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.LeaseNotFoundException;
import com.example.bumblebeebackend.repository.LeaseRepository;
import com.example.bumblebeebackend.model.Lease;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class leaseController {

    @Autowired
    private LeaseRepository leaseRepository;

    @PostMapping("/lease_apply")
    public ResponseEntity<String> apply(@RequestBody Lease lease){
        leaseRepository.save(lease);
        return ResponseEntity.ok("Application for leasing-payment is successful!");
    }

    @GetMapping("/leases")
    List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }


}
