package com.lisseth.inventory.inventory.domain.services;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.common.application.controllers.util.JwtUtil;
import com.lisseth.inventory.inventory.application.models.InventoryResponse;
import com.lisseth.inventory.inventory.domain.models.Inventory;
import com.lisseth.inventory.inventory.domain.models.Product;
import com.lisseth.inventory.inventory.domain.ports.output.GetProductByIdAdapterPort;
import com.lisseth.inventory.inventory.domain.ports.output.GetStockProductAdapterPort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetStockProductServiceTest {

    private GetStockProductService  getStockProductService;

    @Mock
    private GetProductByIdAdapterPort  getProductByIdAdapter;
    @Mock
    private GetStockProductAdapterPort getStockProductAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getStockProductService = new GetStockProductService(getProductByIdAdapter, getStockProductAdapter);
    }

    private final String TOKEN = "Bearer " + JwtUtil.generateToken("admin");

    @Test
    void shouldGetStockProductWhenIdExistsThenReturnInventoryResponse() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        Inventory inventory = Inventory.builder()
                .productId(productId)
                .quantity(30L)
                .build();

        when(getProductByIdAdapter.get(productId, TOKEN)).thenReturn(Optional.of(product));
        when(getStockProductAdapter.find(productId)).thenReturn(Optional.of(inventory));

        InventoryResponse response = getStockProductService.get(productId, TOKEN);

        assertNotNull(response);
        assertEquals(response.getProductId(), productId);
        assertEquals(response.getName(), product.getName());
        assertEquals(response.getQuantity(), inventory.getQuantity());
    }

    @Test
    void shouldThrowExceptionWhenGetProductAndNoExistsThenThrowNoFoundException() {

        var productId = UUID.randomUUID().toString();
        when(getProductByIdAdapter.get(productId, TOKEN)).thenReturn(Optional.empty());
        assertThrows(Exception.NotFoundException.class, () -> getStockProductService.get(productId, TOKEN));
    }

    @Test
    void shouldThrowExceptionWhenGetInventoryAndNoExistsThenThrowNoFoundException() {

        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(getProductByIdAdapter.get(productId, TOKEN)).thenReturn(Optional.of(product));
        when(getStockProductAdapter.find(productId)).thenReturn(Optional.empty());
        assertThrows(Exception.NotFoundException.class, () -> getStockProductService.get(productId, TOKEN));
    }

    @Test
    void  shouldThrowExceptionWhenGetThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(getProductByIdAdapter.get(productId, TOKEN))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getStockProductService.get(productId, TOKEN));
    }

}