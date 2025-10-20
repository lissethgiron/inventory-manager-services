package com.lisseth.product.product.infrastructure.mappers;

import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.infrastructure.entities.ProductEntity;

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
