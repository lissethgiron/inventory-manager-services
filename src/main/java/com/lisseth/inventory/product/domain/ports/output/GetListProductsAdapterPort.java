package com.lisseth.inventory.product.domain.ports.output;

import com.lisseth.inventory.product.domain.models.Product;
import jakarta.persistence.PersistenceException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetListProductsAdapterPort {

    Page<Product> findAll(int page, int size) throws PersistenceException;
}
