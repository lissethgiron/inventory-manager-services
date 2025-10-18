package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductRequest;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.CreateProductServicePort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductControllerTest {

    private ProductController productController;

    @Mock
    private CreateProductServicePort createProductServicePort;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        productController = new ProductController(createProductServicePort);
    }

    @Test
    void shouldCreateProductWhenDataIsValid() {
        Product product = Product.builder()
                .productId("prod_123-123")
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(createProductServicePort.create(any(Product.class))).thenReturn(product);
        final ResponseEntity<JsonApiResponse<ProductResponse>> response = productController
                .createProduct(buildProductRequest());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnExceptionThenThrowPersistenceException() {
        when(createProductServicePort.create(any(Product.class))).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> productController.createProduct(buildProductRequest()));
    }

    private ProductRequest buildProductRequest(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Pañitos humedos");
        productRequest.setPrice(BigDecimal.valueOf(10000.20));

        return productRequest;
    }
}