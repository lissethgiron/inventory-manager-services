package com.lisseth.product.product.domain.services;

import com.lisseth.product.common.domain.config.Exception;
import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.output.GetProductByIdAdapterPort;
import com.lisseth.product.product.domain.ports.output.UpdateProductAdapterPort;
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

class UpdateProductServiceTest {

    private UpdateProductService updateProductService;

    @Mock
    private GetProductByIdAdapterPort getProductByIdAdapter;
    @Mock
    private UpdateProductAdapterPort updateProductAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
        updateProductService = new UpdateProductService(getProductByIdAdapter, updateProductAdapter);
    }

    @Test
    void shouldUpdateProductWhenDataIsValidThenReturnProduct() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.of(product));
        when(updateProductAdapter.save(product)).thenReturn(product);
        Product productUpdated = updateProductService.update(product);

        assertNotNull(productUpdated);
        assertEquals(productUpdated.getProductId(), productId);
    }

    @Test
    void shouldThrowExceptionWhenUpdateProductAndNoExistsThenThrowNoFoundException() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();
        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.empty());
        assertThrows(Exception.NotFoundException.class, () -> updateProductService.update(product));
    }

    @Test
    void  shouldThrowExceptionWhenUpdateProductThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();
        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.of(Product.builder().build()));
        when(updateProductAdapter.save(product)).thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> updateProductService.update(product));
    }

}