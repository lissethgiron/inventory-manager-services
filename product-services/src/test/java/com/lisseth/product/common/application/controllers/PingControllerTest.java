package com.lisseth.product.common.application.controllers;

import com.lisseth.product.common.application.models.JsonApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.MockitoAnnotations.openMocks;

class PingControllerTest {

    private PingController pingController;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        pingController = new PingController();
    }

    @Test
    void shouldPing() {
        final ResponseEntity<JsonApiResponse<String>> response = pingController.ping();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}