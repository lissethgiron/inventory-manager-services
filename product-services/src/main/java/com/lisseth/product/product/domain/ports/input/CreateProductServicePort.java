package com.lisseth.product.product.domain.ports.input;

import com.lisseth.product.product.domain.models.Product;

public interface CreateProductServicePort {

    Product create(Product product);
}
