package com.order.order.controller;

import com.order.order.dto.OrderDto;
import com.order.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /*
curl --location --request POST 'http://localhost:8080/api/v1/order' \
--header 'Content-Type: application/json' \
--data-raw '{
    "items": [
        {
            "productCode": "abc",
            "quantity": 1,
            "unitPrice": 5.4
        },
         {
            "productCode": "abqc",
            "quantity": 1,
            "unitPrice": 5.6
        }
    ]
}'
     */


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@Validated @RequestBody OrderDto orderDto) {

        return orderService.createOrder(orderDto);
    }
}
