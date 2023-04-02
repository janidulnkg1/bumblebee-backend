package com.example.bumblebeebackend.repository;


import com.example.bumblebeebackend.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease,Long> {
}
