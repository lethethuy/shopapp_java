package com.example.shopapp.repositores;

import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
}
