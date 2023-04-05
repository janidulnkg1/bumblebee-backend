package com.example.bumblebeebackend.testing;

import com.example.bumblebeebackend.exception.StockNotFoundException;
import com.example.bumblebeebackend.repository.StockRepository;
import com.example.bumblebeebackend.model.Stock;
import com.example.bumblebeebackend.controller.stockController;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
public class stockControllerTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private stockController stockController;

    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        stock.setItemName("Test Item");
        stock.setItemQuantity(10);

        when(stockRepository.save(stock)).thenReturn(stock);

        ResponseEntity<String> response = stockController.addStock(stock);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Stock Item has been added successfully!");
    }

    @Test
    public void testGetAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("Item 1", 10));
        stocks.add(new Stock("Item 2", 20));
        stocks.add(new Stock("Item 3", 30));

        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> response = stockController.getAllStocks();
        assertThat(response).isEqualTo(stocks);
    }

    @Test
    public void testDeleteStock() {
        Long stockId = 1L;
        when(stockRepository.existsById(stockId)).thenReturn(true);

        String response = stockController.deleteStock(stockId);
        assertThat(response).isEqualTo("Stock Item id " + stockId + " has been deleted!");

        verify(stockRepository, times(1)).deleteById(stockId);
    }

    @Test
    public void testUpdateStock() {
        Long stockId = 1L;
        Stock oldStock = new Stock("Item 1", 10);
        Stock newStock = new Stock("Item 1", 20);

        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.of(oldStock));
        when(stockRepository.save(oldStock)).thenReturn(newStock);

        Stock response = stockController.updateStock(newStock, stockId);
        assertThat(response).isEqualTo(newStock);

        verify(stockRepository, times(1)).save(oldStock);
    }


}
