package com.lisseth.inventory.inventory.domain.ports.output;

import com.lisseth.inventory.inventory.domain.models.Inventory;
import jakarta.persistence.PersistenceException;

import java.util.Optional;

public interface GetStockProductAdapterPort {

    Optional<Inventory> find(String productId) throws PersistenceException;
}
