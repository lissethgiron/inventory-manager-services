package com.lisseth.inventory.inventory.domain.services;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.inventory.application.models.InventoryResponse;
import com.lisseth.inventory.inventory.domain.models.Inventory;
import com.lisseth.inventory.inventory.domain.models.Product;
import com.lisseth.inventory.inventory.domain.ports.input.GetStockProductServicePort;
import com.lisseth.inventory.inventory.domain.ports.output.GetProductByIdAdapterPort;
import com.lisseth.inventory.inventory.domain.ports.output.GetStockProductAdapterPort;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetStockProductService implements GetStockProductServicePort {

    private final GetProductByIdAdapterPort getProductByIdAdapter;
    private final GetStockProductAdapterPort getStockProductAdapter;

    @Override
    public InventoryResponse get(
            String productId,
            String token
    ) throws PersistenceException, Exception.NotFoundException {
        Product product = getProductById(productId, token);

        Optional<Inventory> OptionalInventory = getStockProductAdapter.find(productId);

        if (OptionalInventory.isEmpty()) {
            log.warn("The inventory no exist to product {}", productId);
            throw new Exception.NotFoundException("The inventory no exist to product " +  productId);
        }

        return InventoryResponse.builder()
                .productId(productId)
                .name(product.getName())
                .price(product.getPrice())
                .quantity(OptionalInventory.get().getQuantity())
                .build();
    }

    private Product getProductById(
            String productId,
            String token
    ) throws PersistenceException, Exception.NotFoundException {
        Optional<Product> product = getProductByIdAdapter.get(productId, token);

        if (product.isEmpty()) {
            log.warn("The product no exist {}", productId);
            throw new Exception.NotFoundException("The product no exist " +  productId);
        }

        return product.get();
    }
}
