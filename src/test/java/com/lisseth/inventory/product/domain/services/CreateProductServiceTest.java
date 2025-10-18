package com.lisseth.inventory.product.domain.services;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.output.CreateProductAdapterPort;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

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
        Product product = Product.builder()
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        Product productResponse = Product.builder()
                .productId("prod_123-123")
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(createProductAdapter.save(product)).thenReturn(productResponse);
        Product productCreated = createProductService.create(product);

        assertNotNull(productCreated);
        assertEquals(productCreated.getProductId(), "prod_123-123");
    }

    @Test
    void shouldReturnExceptionThenThrowPersistenceException() {
        when(createProductAdapter.save(any(Product.class))).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> createProductAdapter.save(Product.builder()
                .build()));
    }

}