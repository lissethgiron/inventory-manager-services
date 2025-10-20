package com.lisseth.inventory.inventory.infrastructure.repositories;

import com.lisseth.inventory.inventory.infrastructure.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, String> {

    Optional<InventoryEntity> findByProductId(String productId);
}
