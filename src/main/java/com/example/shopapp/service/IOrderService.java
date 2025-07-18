package com.example.shopapp.service;

import com.example.shopapp.DTO.OrderDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws DataNotFoundException;
    Order getOrder(Long id);
    Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException;
    void deleteOrder(Long id);
    List<Order> findByUserId(Long userId);

}
