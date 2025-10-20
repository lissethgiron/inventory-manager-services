package com.lisseth.product.product.domain.ports.input;

import com.lisseth.product.product.application.models.ProductResponse;
import jakarta.persistence.PersistenceException;
import org.springframework.data.domain.Page;

public interface GetListProductsServicePort {

    Page<ProductResponse> getAll(int page, int size) throws PersistenceException ;
}
