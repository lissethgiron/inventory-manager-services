package com.lisseth.inventory.product.domain.ports.output;

import com.lisseth.inventory.product.domain.models.Product;
import jakarta.persistence.PersistenceException;

import java.util.Optional;

public interface GetProductByIdAdapterPort {

    Optional<Product> findById(String productId) throws PersistenceException;
}
