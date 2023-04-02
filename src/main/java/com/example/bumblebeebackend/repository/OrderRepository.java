package com.example.bumblebeebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bumblebeebackend.model.Order;

public interface OrderRepository extends JpaRepository <Order,Long> {
}
