package com.example.bumblebeebackend.testing;


import com.example.bumblebeebackend.exception.LeaseNotFoundException;
import com.example.bumblebeebackend.model.Lease;
import com.example.bumblebeebackend.repository.LeaseRepository;
import com.example.bumblebeebackend.controller.leaseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class leaseControllerTest {

    @Mock
    private LeaseRepository leaseRepository;

    private leaseController leaseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaseController = new leaseController();
        leaseController.leaseRepository = leaseRepository;
    }

    @Test
    void testAddLease() {
        Lease lease = new Lease();
        doReturn(lease).when(leaseRepository).save(any(Lease.class));
        ResponseEntity<String> responseEntity = leaseController.applyLease(lease);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllLeases() {
        List<Lease> leases = new ArrayList<>();
        leases.add(new Lease());
        doReturn(leases).when(leaseRepository).findAll();
        List<Lease> result = leaseController.getAllLeases();
        assertEquals(leases, result);
    }

    @Test
    void testDeleteLease() {
        Long leaseId = 1L;
        doReturn(true).when(leaseRepository).existsById(leaseId);
        doNothing().when(leaseRepository).deleteById(leaseId);
        String result = leaseController.deleteLease(leaseId);
        assertEquals("Customer Leasing with id " + leaseId + " has been deleted!", result);
    }


    @Test
    void testUpdateLease() {
        Long leaseId = 1L;
        Lease lease = new Lease();
        lease.setPlanStatus("Applicable");
        Optional<Lease> optionalLease = Optional.of(lease);

        doReturn(optionalLease).when(leaseRepository).findById(leaseId);
        doReturn(lease).when(leaseRepository).save(any(Lease.class));

        Lease updatedLease = leaseController.updateLease(new Lease(), leaseId);

        assertEquals(lease.getPlanStatus(), updatedLease.getPlanStatus());
    }


}
