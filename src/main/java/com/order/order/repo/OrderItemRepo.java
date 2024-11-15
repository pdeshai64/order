package com.order.order.repo;

import com.order.order.entity.Order;
import com.order.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findAllByOrder_id(Long orderId);
}
