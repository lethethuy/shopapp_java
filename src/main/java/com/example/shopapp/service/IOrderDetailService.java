package com.example.shopapp.service;

import com.example.shopapp.DTO.OrderDTO;
import com.example.shopapp.DTO.OrderDetailDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.*;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException;
    OrderDetail getOrderDetail(Long id) throws DataNotFoundException;
    OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException;
    void deleteOrderDetail(Long id);
    List<OrderDetail> findByOrderId(Long orderId) throws DataNotFoundException;
}
