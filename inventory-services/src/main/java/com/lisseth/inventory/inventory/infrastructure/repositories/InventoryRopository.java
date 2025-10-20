package com.lisseth.inventory.inventory.infrastructure.repositories;

import com.lisseth.inventory.inventory.infrastructure.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRopository extends JpaRepository<InventoryEntity, String> {
}
