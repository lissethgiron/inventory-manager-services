package com.lisseth.product.product.infrastructure.adapters;

import com.lisseth.product.product.infrastructure.Repositories.ProductRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DeleteProductAdapterTest {

    private DeleteProductAdapter deleteProductAdapter;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        deleteProductAdapter = new DeleteProductAdapter(productRepository);
    }

    @Test
    void shouldDeleteProductWhenDeleteThenReturnBoolean() {
        var productId = UUID.randomUUID().toString();
        doNothing().when(productRepository).deleteById(productId);
        final Boolean response = deleteProductAdapter.delete(productId);
        assertNotNull(response);
        assertTrue(response);
    }

    @Test
    void  shouldThrowExceptionWhenDeleteThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        doThrow(new PersistenceException("Error al eliminar"))
                .when(productRepository)
                .deleteById(productId);
        assertThrows(PersistenceException.class, () -> deleteProductAdapter.delete(productId));
    }

}