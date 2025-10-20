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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetProductByIdAdapterTest  {

    private GetProductByIdAdapter getProductByIdAdapter;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getProductByIdAdapter = new GetProductByIdAdapter(productRepository);
    }

    @Test
    void shouldGetProductByIdWhenFindThenReturnProduct() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        ProductEntity productEntity = ProductMapper.toEntity(product);
        final var productEntityOptional = Optional.of(productEntity);
        when(productRepository.findById(productId)).thenReturn(productEntityOptional);
        final Optional<Product> productResponse = getProductByIdAdapter.findById(productId);
        assertNotNull(productResponse);
        assertTrue(productResponse.isPresent());
        assertEquals(productResponse.get().getProductId(), productEntityOptional.get().getProductId());
    }

    @Test
    void  shouldThrowExceptionWhenFindByIdThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(productRepository.findById(productId))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getProductByIdAdapter.findById(productId));
    }
}