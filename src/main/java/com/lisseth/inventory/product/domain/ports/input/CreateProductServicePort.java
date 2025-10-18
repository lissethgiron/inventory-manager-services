package com.lisseth.inventory.product.domain.ports.input;

import com.lisseth.inventory.product.domain.models.Product;

public interface CreateProductServicePort {

    Product create(Product product);
}
