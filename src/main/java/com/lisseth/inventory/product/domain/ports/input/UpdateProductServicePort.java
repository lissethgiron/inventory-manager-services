package com.lisseth.inventory.product.domain.ports.input;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.product.domain.models.Product;
import jakarta.persistence.PersistenceException;

public interface UpdateProductServicePort {

    Product update(Product product) throws PersistenceException, Exception.NotFoundException;
}
