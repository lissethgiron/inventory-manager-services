package com.lisseth.inventory.product.infrastructure.mappers;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.infrastructure.entities.ProductEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ProductMapper {

    public static ProductEntity toEntity(Product entity) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductId("prod_" + UUID.randomUUID());
        productEntity.setName(entity.getName());
        productEntity.setPrice(entity.getPrice());

        return productEntity;
    }

    public static Product toDomain(ProductEntity entity) {
        log.info("adapter entity {}", entity);
        return Product.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}
