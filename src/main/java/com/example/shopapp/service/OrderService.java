package com.example.shopapp.service;

import com.example.shopapp.DTO.OrderDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.OrderStatus;
import com.example.shopapp.models.*;
import com.example.shopapp.repositores.OrderDetailRepository;
import com.example.shopapp.repositores.OrderRepository;
import com.example.shopapp.repositores.ProductRepository;
import com.example.shopapp.repositores.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final ModelMapper modelMapper;
    @Override
    public Order createOrder(OrderDTO orderDTO) throws DataNotFoundException {
        // Tim xem userId co ton tai hay khong
        User user = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(()->new DataNotFoundException("Cannot find user with id: "+ orderDTO.getUserId()));

        // convert oderDTO => order
        // dung thu vien moderMapper
        // Tao 1 luong anh xa rieng de kiem soat viec anh xa, anh xa tat ca cac truong ngoai tru Id
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        // Cap nhat các trường của đơn hàng từ orderDTO
        Order order = new Order();

        // chuyen tu orderDTO -> order
        modelMapper.map(orderDTO,order);

        // set cac truong order tuong ung
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        // Kiem tra shipping date phai >= ngay hom nay
        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())){
            throw new DataNotFoundException("Date must be at least today");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException {
        Order existingOrder = orderRepository.findById(id).orElseThrow(()->
                new DataNotFoundException("Cannot find order with id "+ id));
        User existingUser = userRepository.findById(orderDTO.getUserId()).orElseThrow(()->
                new DateTimeException("cannot find user witd id "+ id));

        // Tao 1 luong anh xa rieng de kiem soat viec anh xa
        modelMapper.typeMap(OrderDTO.class,Order.class)
                .addMappings(mapper->mapper.skip(Order::setId));
        // Cap nhat cac truong cua don hang tu orderDTO
        modelMapper.map(orderDTO,existingOrder);
        existingOrder.setUser(existingUser);
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        // xoa cung => nen xoa mem
        if (order != null){
//            orderRepository.delete(order.get());
            order.setActive(false);
            orderRepository.save(order);
        }

    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
