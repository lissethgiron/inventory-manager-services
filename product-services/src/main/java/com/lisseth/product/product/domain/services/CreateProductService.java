package com.lisseth.product.product.domain.services;

import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.input.CreateProductServicePort;
import com.lisseth.product.product.domain.ports.output.CreateProductAdapterPort;
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
