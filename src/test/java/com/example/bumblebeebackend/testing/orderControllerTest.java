package com.example.bumblebeebackend.testing;


import com.example.bumblebeebackend.exception.OrderNotFoundException;
import com.example.bumblebeebackend.model.customerOrder;
import com.example.bumblebeebackend.repository.CustomerOrderRepository;
import com.example.bumblebeebackend.controller.orderController;
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

class orderControllerTest {

    @Mock
    private CustomerOrderRepository orderRepository;

    private orderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderController = new orderController();
        orderController.customerOrderRepository = orderRepository;
    }

    @Test
    void testAddOrder() {
        customerOrder order = new customerOrder();
        doReturn(order).when(orderRepository).save(any(customerOrder.class));
        ResponseEntity<String> responseEntity = orderController.addOrder(order);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllOrders() {
        List<customerOrder> orders = new ArrayList<>();
        orders.add(new customerOrder());
        doReturn(orders).when(orderRepository).findAll();
        List<customerOrder> result = orderController.getAllOrders();
        assertEquals(orders, result);
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;
        doReturn(true).when(orderRepository).existsById(orderId);
        doNothing().when(orderRepository).deleteById(orderId);
        String result = orderController.deleteOrder(orderId);
        assertEquals("Order with id " + orderId + " has been deleted!", result);
    }

    @Test
    void testUpdateLease() {
        Long orderId = 1L;
        customerOrder order = new customerOrder();
        order.setPaymentStatus("PAID");
        Optional<customerOrder> optionalOrder = Optional.of(order);

        doReturn(optionalOrder).when(orderRepository).findById(orderId);
        doReturn(order).when(orderRepository).save(any(customerOrder.class));

        customerOrder updatedOrder = orderController.updateOrder(new customerOrder(), orderId);

        assertEquals(order.getPaymentStatus(), updatedOrder.getPaymentStatus());
    }


}
