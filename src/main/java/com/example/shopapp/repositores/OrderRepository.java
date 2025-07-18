package com.example.shopapp.repositores;

import com.example.shopapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // tim cac order by UserId
    List<Order> findByUserId(Long userId);

}
