package com.lisseth.inventory.common.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.common.domain.config.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JsonApiResponse<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseController.error("validate-error", HttpStatus.BAD_REQUEST.value(), errors);
    }

    @ExceptionHandler(Exception.NotFoundException.class)
    public ResponseEntity<JsonApiResponse<Object>> handleNotFoundException(Exception.NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.put("title", "Not Found");
        error.put("detail", ex.getMessage());

        return ResponseController.error("validate-error", HttpStatus.NOT_FOUND.value(), error);
    }
}
