package com.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorDto {
    private String message;

    private List<DetailsErrorDto> errors;
}
