package com.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailsErrorDto {

    private String field;
    private String message;
}
