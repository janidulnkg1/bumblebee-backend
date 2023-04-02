package com.example.bumblebeebackend.repository;

import com.example.bumblebeebackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByemail(String email);
}
