package com.lisseth.inventory.product.domain.ports.output;

import jakarta.persistence.PersistenceException;

public interface DeleteProductAdapterPort {

    void delete(String productId) throws PersistenceException;
}
