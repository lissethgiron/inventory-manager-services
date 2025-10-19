package com.lisseth.inventory.product.infrastructure.adapters;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import com.lisseth.inventory.product.infrastructure.entities.ProductEntity;
import com.lisseth.inventory.product.infrastructure.mappers.ProductMapper;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetListProductsAdapterTest {

    private GetListProductsAdapter getListProductsAdapter;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getListProductsAdapter = new GetListProductsAdapter(productRepository);
    }

    @Test
    void shouldGetAllProductByIdWhenFindThenReturnProducts() {
        Product product1 = Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000))
                .build();

        Product product2 = Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos con Aloee")
                .price(BigDecimal.valueOf(12000))
                .build();


        ProductEntity productEntity1 = ProductMapper.toEntity(product1);
        ProductEntity productEntity2 = ProductMapper.toEntity(product2);
        List<ProductEntity> entities = List.of(productEntity1, productEntity2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductEntity> entityPage = new PageImpl<>(entities, pageable, entities.size());
        when(productRepository.findAll(pageable)).thenReturn(entityPage);
        final Page<Product> productResponse = getListProductsAdapter.findAll(0 , 10);

        assertNotNull(productResponse);
        assertEquals(2, productResponse.getContent().size());
        assertEquals("Pañitos humedos", productResponse.getContent().get(0).getName());
        assertEquals("Pañitos humedos con Aloee", productResponse.getContent().get(1).getName());
    }

    @Test
    void  shouldThrowExceptionWhenFindAllThenThrowPersistenceException() {
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.findAll(pageable)).thenThrow(new jakarta.persistence.PersistenceException("DB error"));
        assertThrows(PersistenceException.class, () -> getListProductsAdapter.findAll(0, 10));
    }
}