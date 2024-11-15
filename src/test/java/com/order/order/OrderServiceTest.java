package com.order.order;

import com.order.order.dto.OrderDto;
import com.order.order.dto.OrderItemDto;
import com.order.order.entity.Order;
import com.order.order.entity.OrderItem;
import com.order.order.entity.OrderStatus;
import com.order.order.repo.OrderItemRepo;
import com.order.order.repo.OrderRepo;
import com.order.order.service.OrderService;
import com.order.order.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    OrderItemRepo orderItemRepo;

    @Mock
    OrderRepo orderRepo;

    OrderServiceImpl orderService;

    @BeforeEach
    void init() {
        orderService  = new OrderServiceImpl(orderRepo, orderItemRepo);
    }

    @Test
    void testCreateOrderMethod() {
        OrderItemDto orderItemDto = Mockito.mock(OrderItemDto.class);
        Mockito.when(orderItemDto.getUnitPrice()).thenReturn(BigDecimal.ONE);
        Mockito.when(orderItemDto.getQuantity()).thenReturn(1);
        Mockito.when(orderItemDto.getProductCode()).thenReturn("abc");

        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        when(orderRepo.save(any())).thenReturn(order);

        OrderDto orderDto = new OrderDto();
        orderDto.setItems(List.of(orderItemDto));

        orderService.createOrder(orderDto);

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderRepo, times(2)).save(orderArgumentCaptor.capture());
        assertEquals(orderArgumentCaptor.getValue().getStatus(), OrderStatus.CREATED);

        ArgumentCaptor<List<OrderItem>> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(orderItemRepo, times(1)).saveAll(listArgumentCaptor.capture());
        OrderItem orderItem = listArgumentCaptor.getValue().get(0);
        assertEquals(BigDecimal.ONE, orderItem.getUnitPrice());
        assertEquals("abc", orderItem.getProductCode());
        assertEquals(1, orderItem.getQuantity());

    }
}
