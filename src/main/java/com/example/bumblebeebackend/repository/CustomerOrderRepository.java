package com.example.bumblebeebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bumblebeebackend.model.customerOrder;

public interface CustomerOrderRepository extends JpaRepository <customerOrder,Long> {
}
