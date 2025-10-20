package com.lisseth.product.product.infrastructure.adapters;

import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.infrastructure.Repositories.ProductRepository;
import com.lisseth.product.product.infrastructure.entities.ProductEntity;
import com.lisseth.product.product.infrastructure.mappers.ProductMapper;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateProductAdapterTest {

    private CreateProductAdapter createProductAdapter;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createProductAdapter = new CreateProductAdapter(productRepository);
    }

    @Test
    void shouldCreateProductWhenSaveThenReturnProduct() {
        Product product = Product.builder()
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        ProductEntity productEntity = ProductMapper.toEntity(product);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        Product result = createProductAdapter.save(product);

        assertNotNull(result);
        assertNotNull(result.getProductId());
        assertEquals("Pañitos humedos", result.getName());
        assertEquals(BigDecimal.valueOf(10000.20), result.getPrice());
    }

    @Test
    void  shouldThrowExceptionWhenSaveProductIsNullThenThrowPersistenceException() {
        when(productRepository.save(any(ProductEntity.class)))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> createProductAdapter.save(Product.builder().build()));
    }
}