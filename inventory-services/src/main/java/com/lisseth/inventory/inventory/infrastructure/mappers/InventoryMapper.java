package com.lisseth.inventory.inventory.infrastructure.mappers;

import com.lisseth.inventory.inventory.domain.models.Inventory;
import com.lisseth.inventory.inventory.infrastructure.entities.InventoryEntity;

public class InventoryMapper {

    public static Inventory toDomain(InventoryEntity entity) {
        return Inventory.builder()
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .build();
    }
}
