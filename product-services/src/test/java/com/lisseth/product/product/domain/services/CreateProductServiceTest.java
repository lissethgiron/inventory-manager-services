package com.lisseth.product.product.domain.services;

import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.output.CreateProductAdapterPort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateProductServiceTest {

    private CreateProductService createProductService;

    @Mock
    private CreateProductAdapterPort createProductAdapter;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createProductService = new CreateProductService(createProductAdapter);
    }

    @Test
    void shouldCreateProductWhenDataIsValidThenReturnProduct() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        Product productResponse = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();
        when(createProductAdapter.save(product)).thenReturn(productResponse);
        Product productCreated = createProductService.create(product);

        assertNotNull(productCreated);
        assertEquals(productCreated.getProductId(), productId);
    }

    @Test
    void shouldReturnExceptionThenThrowPersistenceException() {
        when(createProductAdapter.save(any(Product.class))).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> createProductService.create(Product.builder()
                .build()));
    }

}