package com.lisseth.inventory.inventory.infrastructure.adapters;

import com.lisseth.inventory.common.application.controllers.util.JwtUtil;
import com.lisseth.inventory.inventory.domain.models.Product;
import com.lisseth.inventory.inventory.infrastructure.models.ProductResponse;
import com.lisseth.inventory.inventory.infrastructure.repositories.ProductRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetProductByIdAdapterTest {

    private GetProductByIdAdapter getProductByIdAdapter;

    @Mock
    private ProductRepository  productRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getProductByIdAdapter = new GetProductByIdAdapter(productRepository);
    }

    private final String TOKEN = "Bearer " + JwtUtil.generateToken("admin");

    @Test
    void shouldGetProductByIdWhenFindThenReturnProduct() {
        var productId = UUID.randomUUID().toString();
        when(productRepository.getProductById(productId, TOKEN)).thenReturn(buildProductResponse(productId));
        final Optional<Product> productResponse = getProductByIdAdapter.get(productId, TOKEN);
        assertNotNull(productResponse);
        assertTrue(productResponse.isPresent());
    }

    @Test
    void shouldGetProductByIdWhenProductResponseThenReturnProductEmpty() {
        var productId = UUID.randomUUID().toString();
        when(productRepository.getProductById(productId, TOKEN)).thenReturn(null);

        final Optional<Product> productResponse = getProductByIdAdapter.get(productId, TOKEN);
        assertTrue(productResponse.isEmpty());
    }

    private ProductResponse buildProductResponse(String productId) {
        ProductResponse productResponse = new ProductResponse();
        ProductResponse.Data data = new ProductResponse.Data();
        ProductResponse.Data.Product product = new ProductResponse.Data.Product();

        product.setProductId(productId);
        product.setName("Product Name");
        product.setProductId(String.valueOf(1000));

        data.setAttributes(product);
        productResponse.setData(data);

        return productResponse;
    }

    @Test
    void  shouldThrowExceptionWhenGetByIdThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(productRepository.getProductById(productId, TOKEN))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getProductByIdAdapter.get(productId, TOKEN));
    }
}