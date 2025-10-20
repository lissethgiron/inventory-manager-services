package com.lisseth.product.product.domain.ports.output;

import com.lisseth.product.product.domain.models.Product;
import jakarta.persistence.PersistenceException;

import java.util.Optional;

public interface GetProductByIdAdapterPort {

    Optional<Product> findById(String productId) throws PersistenceException;
}
