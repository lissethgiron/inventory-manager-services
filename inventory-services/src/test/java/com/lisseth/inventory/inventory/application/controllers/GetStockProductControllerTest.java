package com.lisseth.inventory.inventory.application.controllers;

import com.lisseth.inventory.common.application.controllers.util.JwtUtil;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.inventory.application.models.InventoryResponse;
import com.lisseth.inventory.inventory.domain.ports.input.GetStockProductServicePort;
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

class GetStockProductControllerTest {

    private GetStockProductController getStockProductController;

    @Mock
    private GetStockProductServicePort getStockProductService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getStockProductController = new GetStockProductController(getStockProductService);
    }

    private final String TOKEN = "Bearer " + JwtUtil.generateToken("admin");

    @Test
    void shouldGetInventoryWhenDataIsValidThenReturnStockInventory() {
        var productId = UUID.randomUUID().toString();
        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .quantity(20L)
                .build();

        when(getStockProductService.get(productId, TOKEN)).thenReturn(inventoryResponse);
        final ResponseEntity<JsonApiResponse<InventoryResponse>> response = getStockProductController
                .getStock(TOKEN, productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnExceptionWhenGetStockThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(getStockProductService.get(productId, TOKEN)).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getStockProductController.getStock(TOKEN, productId));
    }

}