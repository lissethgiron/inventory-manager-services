package com.lisseth.product.common.application.controllers;

import com.lisseth.product.common.application.models.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthControllerTest {

    private AuthController authController;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        authController = new AuthController();
    }

    @Test
    void shouldGenerateTokenWhenLoginThenReturnToken() {
        LoginRequest login = LoginRequest.builder()
                .username("admin")
                .password("1020")
                .build();

        final ResponseEntity<?> Token = authController.login(login);

        assertNotNull(Token);
        assertEquals(HttpStatus.OK, Token.getStatusCode());
    }

    @Test
    void shouldThrowExceptionWhenGenerateTokenThenThrowUnauthorizedException() {
        LoginRequest login = LoginRequest.builder()
                .username("admin1")
                .password("1582")
                .build();

        ResponseEntity<?> Token = authController.login(login);
        assertNotNull(Token);
        assertEquals(HttpStatus.UNAUTHORIZED, Token.getStatusCode());
    }
}