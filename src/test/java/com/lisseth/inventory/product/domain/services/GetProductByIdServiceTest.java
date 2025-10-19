package com.lisseth.inventory.product.domain.services;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.output.GetProductByIdAdapterPort;
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

class GetProductByIdServiceTest {

    private GetProductByIdService getProductByIdService;

    @Mock
    private GetProductByIdAdapterPort getProductByIdAdapter;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getProductByIdService = new GetProductByIdService(getProductByIdAdapter);
    }

    @Test
    void shouldGetProductWhenIdExistsThenReturnProduct() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.of(product));
        Product response = getProductByIdService.getById(productId);

        assertNotNull(response);
        assertEquals(response.getProductId(), productId);
    }

    @Test
    void shouldThrowExceptionWhenGetByIdAndIdNoExistsThenThrowNoFoundException() {

        var productId = UUID.randomUUID().toString();
        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.empty());
        assertThrows(Exception.NotFoundException.class, () -> getProductByIdService.getById(productId));
    }

    @Test
    void  shouldThrowExceptionWhenGetByIdThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(getProductByIdAdapter.findById(productId))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getProductByIdService.getById(productId));
    }
}