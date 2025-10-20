package com.lisseth.product.product.domain.ports.output;

import jakarta.persistence.PersistenceException;

public interface DeleteProductAdapterPort {

    Boolean delete(String productId) throws PersistenceException;
}
