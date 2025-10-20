package com.lisseth.product.product.domain.ports.input;

import com.lisseth.product.common.domain.config.Exception;
import com.lisseth.product.product.domain.models.Product;
import jakarta.persistence.PersistenceException;

public interface UpdateProductServicePort {

    Product update(Product product) throws PersistenceException, Exception.NotFoundException;
}
