package com.lisseth.product.product.domain.services;

import com.lisseth.product.product.application.models.ProductResponse;
import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.output.GetListProductsAdapterPort;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetListProductsServiceTest {

    private GetListProductsService getListProductsService;

    @Mock
    private GetListProductsAdapterPort getListProductsAdapter;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getListProductsService = new GetListProductsService(getListProductsAdapter);
    }

    @Test
    void shouldGetAllProductThenReturnProducts() {
        Product product1 = Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000))
                .build();

        Product product2 = Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos con Aloe")
                .price(BigDecimal.valueOf(12000))
                .build();

        List<Product> ProductsList = List.of(product1, product2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(ProductsList, pageable, ProductsList.size());

        when(getListProductsAdapter.findAll(0, 10)).thenReturn(productPage);
        Page<ProductResponse> response = getListProductsService.getAll(0, 10);

        assertNotNull(response);
        assertEquals(2, response.getContent().size());
        assertEquals("Pañitos humedos", response.getContent().get(0).getName());
        assertEquals("Pañitos humedos con Aloe", response.getContent().get(1).getName());
    }

    @Test
    void shouldReturnExceptionWhenGetAllThenThrowPersistenceException() {
        when(getListProductsAdapter.findAll(0, 10)).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getListProductsService.getAll(0, 10));
    }

}