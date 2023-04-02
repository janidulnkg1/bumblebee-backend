package com.example.bumblebeebackend.repository;

import com.example.bumblebeebackend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
}
