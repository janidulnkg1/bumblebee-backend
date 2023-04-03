package com.example.bumblebeebackend.controller;

import com.example.bumblebeebackend.exception.StockNotFoundException;

import com.example.bumblebeebackend.exception.UserNotFoundException;
import com.example.bumblebeebackend.repository.StockRepository;
import com.example.bumblebeebackend.model.Stock;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class stockController {

    @Autowired
    private StockRepository stockRepository;

    @PostMapping("/stock_additem")
    public ResponseEntity<String> apply(@RequestBody Stock stock){
        stockRepository.save(stock);
        return ResponseEntity.ok("Stock Item has been added successfully!");
    }

    @GetMapping("/stocks")
    List<Stock> getAllLeases() {

        return stockRepository.findAll();
    }

    @DeleteMapping("/stocks/{stockid}")
    String deleteStock(@PathVariable Long stockid){
        if(!stockRepository.existsById(stockid)){
            throw new UserNotFoundException(stockid);
        }
        stockRepository.deleteById(stockid);
        return "Stock Item id "+stockid+" has been deleted!";
    }


}
