package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductRequest;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.UpdateProductServicePort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateProductControllerTest {

    private UpdateProductController updateProductController;

    @Mock
    private UpdateProductServicePort updateProductService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateProductController = new UpdateProductController(updateProductService);
    }

    @Test
    void shouldUpdateProductWhenDataIsValidThenReturnProduct() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder().productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(updateProductService.update(any(Product.class))).thenReturn(product);
        final ResponseEntity<JsonApiResponse<ProductResponse>> response = updateProductController
                .updateProduct(productId, buildProductRequest());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnExceptionWhenUpdateThenThrowPersistenceException() {var productId = UUID.randomUUID().toString();
        when(updateProductService.update(any(Product.class))).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> updateProductController
                .updateProduct(productId, buildProductRequest()));
    }

    private ProductRequest buildProductRequest(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Pañitos humedos");
        productRequest.setPrice(BigDecimal.valueOf(10000.20));

        return productRequest;
    }
}