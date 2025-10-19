package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.GetProductByIdServicePort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetProductByIdControllerTest {

    private GetProductByIdController  getProductByIdController;

    @Mock
    private GetProductByIdServicePort  getProductByIdService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getProductByIdController = new GetProductByIdController(getProductByIdService);
    }

    @Test
    void shouldCreateProductWhenDataIsValid() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(getProductByIdService.getById(productId)).thenReturn(product);
        final ResponseEntity<JsonApiResponse<ProductResponse>> response = getProductByIdController
                .getProduct(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnExceptionThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(getProductByIdService.getById(productId)).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getProductByIdController.getProduct(productId));
    }

}