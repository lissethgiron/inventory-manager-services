package com.lisseth.inventory.product.domain.services;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.CreateProductServicePort;
import com.lisseth.inventory.product.domain.ports.output.CreateProductAdapterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProductServicePort {

    private final CreateProductAdapterPort createProductAdapter;

    @Override
    public Product create(Product product) {

        return createProductAdapter.save(product);
    }
}
