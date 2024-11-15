package com.order.order.service;

import com.order.order.dto.OrderDto;
import com.order.order.dto.OrderItemDto;
import com.order.order.entity.Order;
import com.order.order.entity.OrderItem;
import com.order.order.entity.OrderStatus;
import com.order.order.repo.OrderItemRepo;
import com.order.order.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        //we can use model mapper here to convert dto to entity. but going with traditional approach due to time.

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDateTime.now()); //this we can do using auditable
        order.setStatus(OrderStatus.CREATED);

        Order savedORder = orderRepo.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal sum= BigDecimal.ZERO;
        for (OrderItemDto itemDto: orderDto.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedORder);
            orderItem.setProductCode(itemDto.getProductCode());
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setUnitPrice(itemDto.getUnitPrice());
            orderItems.add(orderItem);
            sum = sum.add(itemDto.getUnitPrice());

        }

        orderItemRepo.saveAll(orderItems);

        savedORder.setTotalAmount(sum);


        orderRepo.save(savedORder);

        OrderDto response = new OrderDto();
        response.setOrderNumber(savedORder.getOrderNumber());
        response.setStatus(savedORder.getStatus().toString());
        response.setTotalAmount(savedORder.getTotalAmount());
        response.setCreatedAt(savedORder.getCreatedAt());

        List<OrderItem> responseItem = orderItemRepo.findAllByOrder_id(savedORder.getId());
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItem item: responseItem) {
            orderItemDtos.add(OrderItemDto.builder().productCode(item.getProductCode())
                    .quantity(item.getQuantity()).unitPrice(item.getUnitPrice()).build());
        }

        response.setItems(orderItemDtos);

        return response;
    }
}
