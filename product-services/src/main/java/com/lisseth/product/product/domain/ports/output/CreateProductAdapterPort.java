package com.lisseth.product.product.domain.ports.output;

import com.lisseth.product.product.domain.models.Product;

public interface CreateProductAdapterPort {

    Product save(Product product);
}
