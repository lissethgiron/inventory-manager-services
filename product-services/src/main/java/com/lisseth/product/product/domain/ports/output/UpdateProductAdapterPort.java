package com.lisseth.product.product.domain.ports.output;

import com.lisseth.product.product.domain.models.Product;
import jakarta.persistence.PersistenceException;

public interface UpdateProductAdapterPort {

    Product save(Product product) throws PersistenceException;
}
