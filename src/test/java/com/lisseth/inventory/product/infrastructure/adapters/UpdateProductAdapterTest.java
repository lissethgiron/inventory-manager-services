package com.lisseth.inventory.product.infrastructure.adapters;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import com.lisseth.inventory.product.infrastructure.entities.ProductEntity;
import com.lisseth.inventory.product.infrastructure.mappers.ProductMapper;
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

class UpdateProductAdapterTest {

    private UpdateProductAdapter updateProductAdapter;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateProductAdapter = new UpdateProductAdapter(productRepository);
    }

    @Test
    void shouldUpdateProductWhenSaveThenReturnProduct() {
        Product product = Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000))
                .build();
        ProductEntity productEntity = ProductMapper.toEntity(product);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        Product result = updateProductAdapter.save(product);

        assertNotNull(result);
        assertNotNull(result.getProductId());
        assertEquals("Pañitos humedos", result.getName());
        assertEquals(BigDecimal.valueOf(10000), result.getPrice());
    }

    @Test
    void  shouldThrowExceptionWhenSaveThenThrowPersistenceException() {
        when(productRepository.save(any(ProductEntity.class)))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> updateProductAdapter.save(Product.builder().build()));
    }
}