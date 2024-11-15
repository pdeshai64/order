package com.order.order;

import com.order.order.dto.OrderDto;
import com.order.order.dto.OrderItemDto;
import com.order.order.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Test
    void testCreateOrderMethod() {
        OrderItemDto orderItemDto = Mockito.mock(OrderItemDto.class);
        Mockito.when(orderItemDto.getUnitPrice()).thenReturn(BigDecimal.ONE);
        Mockito.when(orderItemDto.getQuantity()).thenReturn(1);
        Mockito.when(orderItemDto.getProductCode()).thenReturn("abc");

        OrderDto orderDto = new OrderDto();
        //orderDto.setItems(List.of(orderItemDto));


    }
}
