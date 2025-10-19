package com.lisseth.inventory.product.infrastructure.mappers;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.infrastructure.entities.ProductEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductEntity toEntity(Product entity) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductId("prod_" + UUID.randomUUID());
        productEntity.setName(entity.getName());
        productEntity.setPrice(entity.getPrice());

        return productEntity;
    }

    public static ProductEntity toEntityUpdate(Product entity) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductId(entity.getProductId());
        productEntity.setName(entity.getName());
        productEntity.setPrice(entity.getPrice());

        return productEntity;
    }

    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}
