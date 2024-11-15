package com.order.order.exception;

import com.order.order.dto.DetailsErrorDto;
import com.order.order.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobleExceptionHandler {

    //likewise we can handle both Exceptoin and runtime exception

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder()
                        .message("Invalid Parameter")
                        .errors(processFieldErrors(result.getFieldErrors())).build());
    }

    private List<DetailsErrorDto> processFieldErrors(List<FieldError> fieldErrors) {
        List<DetailsErrorDto> list = new ArrayList<>();
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            list.add(new DetailsErrorDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return list;
    }
}
