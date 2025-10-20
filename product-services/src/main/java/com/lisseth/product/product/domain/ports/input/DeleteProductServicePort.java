package com.lisseth.product.product.domain.ports.input;

import com.lisseth.product.common.domain.config.Exception;
import jakarta.persistence.PersistenceException;

public interface DeleteProductServicePort {

    Boolean delete(String productId) throws PersistenceException, Exception.NotFoundException;
}
