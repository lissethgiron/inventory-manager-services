package com.lisseth.inventory.product.domain.ports.input;

import com.lisseth.inventory.product.application.models.ProductResponse;
import jakarta.persistence.PersistenceException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetListProductsServicePort {

    Page<ProductResponse> getAll(int page, int size) throws PersistenceException ;
}
