package com.example.shopapp.service;

import com.example.shopapp.DTO.OrderDetailDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.*;
import com.example.shopapp.models.OrderDetail;
import com.example.shopapp.repositores.OrderDetailRepository;
import com.example.shopapp.repositores.OrderRepository;
import com.example.shopapp.repositores.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.List;
@RequiredArgsConstructor
@Service
public class OrderDetailService implements IOrderDetailService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        // Tim orderId co ton tai hay ko
        Order order = orderRepository.findById(orderDetailDTO.getOrderId()).orElseThrow(()->
                new DateTimeException("Cannot find Order with id : "+orderDetailDTO.getOrderId()));

        // Tim product theo id
        Product product = productRepository.findById(orderDetailDTO.getProductId()).orElseThrow(()->
                new DateTimeException("Cannot find Product with id : "+orderDetailDTO.getProductId()));

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .totalMoney(orderDetailDTO.getTotalMoney())
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .price(orderDetailDTO.getPrice())
                .color(orderDetailDTO.getColor())
                .build();

        // luu vao DB
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Cannot find OrderDetail with id: "+id));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        // tim xem order detail co on tai hay ko/ cai nay co the ao constrain tren database
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id).orElseThrow(()->
                new DataNotFoundException("Cannot find order detail with id: "+ id));

        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId()).orElseThrow(()->
                new DateTimeException("Cannot find order with id: "+ orderDetailDTO.getOrderId()));

        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId()).orElseThrow(()->
                new DateTimeException("Cannot find product with id: "+ orderDetailDTO.getProductId()));

        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
     orderDetailRepository.deleteById(id);

    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId)  {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
