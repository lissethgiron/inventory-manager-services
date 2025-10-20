package com.lisseth.product.product.domain.ports.output;

import com.lisseth.product.product.domain.models.Product;
import jakarta.persistence.PersistenceException;
import org.springframework.data.domain.Page;

public interface GetListProductsAdapterPort {

    Page<Product> findAll(int page, int size) throws PersistenceException;
}
