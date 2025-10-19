package com.lisseth.inventory.product.domain.ports.output;

import com.lisseth.inventory.product.domain.models.Product;
import jakarta.persistence.PersistenceException;

public interface UpdateProductAdapterPort {

    Product save(Product product) throws PersistenceException;
}
