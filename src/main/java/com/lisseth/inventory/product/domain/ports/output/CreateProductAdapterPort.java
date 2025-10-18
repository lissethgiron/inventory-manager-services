package com.lisseth.inventory.product.domain.ports.output;

import com.lisseth.inventory.product.domain.models.Product;

public interface CreateProductAdapterPort {

    Product save(Product product);
}
