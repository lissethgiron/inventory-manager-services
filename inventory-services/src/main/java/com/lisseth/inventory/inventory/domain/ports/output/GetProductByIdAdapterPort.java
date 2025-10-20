package com.lisseth.inventory.inventory.domain.ports.output;

import com.lisseth.inventory.inventory.domain.models.Product;

import java.util.Optional;

public interface GetProductByIdAdapterPort {

    Optional<Product> get(String productId, String token);
}
