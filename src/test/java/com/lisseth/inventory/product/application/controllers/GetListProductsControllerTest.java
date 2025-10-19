package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.GetListProductsServicePort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetListProductsControllerTest {

    private GetListProductsController getListProductsController;

    @Mock
    private GetListProductsServicePort getListProductsService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getListProductsController = new GetListProductsController(getListProductsService);
    }

    @Test
    void shouldGetAllProductWhenDataIsValidThenReturnProducts() {
        ProductResponse product1 = new ProductResponse(Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000))
                .build());

        ProductResponse product2 = new ProductResponse(Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos con Aloe")
                .price(BigDecimal.valueOf(12000))
                .build());

        List<ProductResponse> ProductsList = List.of(product1, product2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductResponse> productPage = new PageImpl<>(ProductsList, pageable, ProductsList.size());

        when(getListProductsService.getAll(0, 10)).thenReturn(productPage);
        final ResponseEntity<JsonApiResponse<List<ProductResponse>>> response = getListProductsController
                .getProduct(0, 10);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnExceptionWhenGetProductThenThrowPersistenceException() {
        when(getListProductsService.getAll(0 , 10)).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getListProductsController.getProduct(0 , 10));
    }
}