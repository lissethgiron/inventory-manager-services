package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.domain.ports.input.DeleteProductServicePort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DeleteProductControllerTest {

    private DeleteProductController deleteProductController;
    @Mock
    private DeleteProductServicePort deleteProductService;

    @BeforeEach public void setUp() {
        openMocks(this);
        deleteProductController = new DeleteProductController(deleteProductService);
    }

    @Test
    void shouldDeleteProductDeleteProductIdIsValidThenReturnTrue() {
        var productId = UUID.randomUUID().toString();

        when(deleteProductService.delete(productId)).thenReturn(Boolean.TRUE);
        final ResponseEntity<JsonApiResponse<Boolean>> response = deleteProductController.deleteProduct(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnExceptionWhenDeleteThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(deleteProductService.delete(productId)).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> deleteProductController.deleteProduct(productId));
    }
}