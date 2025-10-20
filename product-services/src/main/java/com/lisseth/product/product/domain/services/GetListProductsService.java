package com.lisseth.product.product.domain.services;

import com.lisseth.product.product.application.models.ProductResponse;
import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.input.GetListProductsServicePort;
import com.lisseth.product.product.domain.ports.output.GetListProductsAdapterPort;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Slf4j
@Service @RequiredArgsConstructor
public class GetListProductsService implements GetListProductsServicePort {

    private final GetListProductsAdapterPort  getListProductsAdapter;

    @Override
    public Page<ProductResponse> getAll(int page, int size) throws PersistenceException {
        Page<Product> Products = getListProductsAdapter.findAll(page, size);

        return Products.map(GetListProductsService::toResponse);
    }

    public static ProductResponse toResponse(Product Product) {
        return new ProductResponse(Product);
    }
}
