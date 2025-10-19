package com.lisseth.inventory.product.domain.services;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.output.DeleteProductAdapterPort;
import com.lisseth.inventory.product.domain.ports.output.GetProductByIdAdapterPort;
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

class DeleteProductServiceTest {

    private DeleteProductService deleteProductService;

    @Mock
    private GetProductByIdAdapterPort  getProductByIdAdapter;

    @Mock
    private DeleteProductAdapterPort deleteProductAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
        deleteProductService = new DeleteProductService(getProductByIdAdapter, deleteProductAdapter);
    }

    @Test
    void shouldDeleteProductWhenIdExistsThenReturnTrue() {
        var productId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productId(productId)
                .name("Pañitos humedos")
                .price(BigDecimal.valueOf(10000.20))
                .build();

        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.of(product));
        when(deleteProductAdapter.delete(productId)).thenReturn(Boolean.TRUE);
        Boolean response = deleteProductService.delete(productId);

        assertNotNull(response);
        assertTrue(response);
    }

    @Test
    void shouldThrowExceptionWhenDeleteProductAndNoExistsThenThrowNoFoundException() {
        var productId = UUID.randomUUID().toString();
        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.empty());
        assertThrows(Exception.NotFoundException.class, () -> deleteProductService.delete(productId));
    }

    @Test
    void  shouldThrowExceptionWhenDeleteProductThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(getProductByIdAdapter.findById(productId)).thenReturn(Optional.of(Product.builder().build()));
        when(deleteProductAdapter.delete(productId)).thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> deleteProductService.delete(productId));
    }

}