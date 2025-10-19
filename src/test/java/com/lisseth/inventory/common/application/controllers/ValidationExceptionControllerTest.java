package com.lisseth.inventory.common.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.common.domain.config.Exception;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@Slf4j
class ValidationExceptionControllerTest {

    private ValidationExceptionController  validationExceptionController;

    @BeforeEach
    void setUp() {
        openMocks(this);
        validationExceptionController = new ValidationExceptionController();
    }

    @Test
    void testHandleValidationExceptions() {
        FieldError error1 = new FieldError(
                "product", "name", "El nombre mo debe ser null");
        FieldError error2 = new FieldError(
                "product", "price", "El precio no debe ser null");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(List.of(error1, error2));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<JsonApiResponse<Object>> response = validationExceptionController.handleValidationExceptions(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void testHandleNotFoundException() {
        Exception.NotFoundException ex = new Exception.NotFoundException("The product no exist");
        ResponseEntity<JsonApiResponse<Object>> response = validationExceptionController.handleNotFoundException(ex);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testHandleUnauthorizedException() {
        Exception.UnauthorizedException ex = new Exception.UnauthorizedException("Invalid or expired token");
        ResponseEntity<JsonApiResponse<Object>> response = validationExceptionController.handleUnauthorizedException(ex);
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}