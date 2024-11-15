package com.order.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Data
@Validated
@Builder
@AllArgsConstructor
public class OrderItemDto {

    @NotEmpty
    @NotNull
    private String productCode;
    @Positive(message = "Qty must be grater than zero")
    private Integer quantity;
    @Positive
    private BigDecimal unitPrice;
}
