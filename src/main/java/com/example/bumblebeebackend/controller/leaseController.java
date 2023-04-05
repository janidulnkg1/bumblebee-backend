package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.AdminNotFoundException;
import com.example.bumblebeebackend.exception.LeaseNotFoundException;
import com.example.bumblebeebackend.exception.OrderNotFoundException;
import com.example.bumblebeebackend.exception.UserNotFoundException;
import com.example.bumblebeebackend.model.customerOrder;
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
    public ResponseEntity<String> applyLease(@RequestBody Lease lease){
        leaseRepository.save(lease);
        return ResponseEntity.ok("Application for leasing-payment is successful!");
    }

    @GetMapping("/leases")
    List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    @DeleteMapping("/lease/{leaseid}")
    String deleteLease(@PathVariable Long leaseid){
        if(!leaseRepository.existsById(leaseid)){
            throw new AdminNotFoundException(leaseid);
        }
        leaseRepository.deleteById(leaseid);
        return "Customer Leasing with id "+leaseid+" has been deleted!";
    }

    @PutMapping("/lease/{leaseid}")
    Lease updateLease(@RequestBody Lease newlease, @PathVariable Long leaseid){
        return leaseRepository.findById(leaseid)
                .map(lease -> {
                    lease.setPlanStatus(newlease.getPlanStatus());
                    return  leaseRepository.save(lease);
                }).orElseThrow(()->new LeaseNotFoundException(leaseid));
    }




}
