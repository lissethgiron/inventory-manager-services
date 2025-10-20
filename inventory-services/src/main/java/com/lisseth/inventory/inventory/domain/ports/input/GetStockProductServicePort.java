package com.lisseth.inventory.inventory.domain.ports.input;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.inventory.application.models.InventoryResponse;
import jakarta.persistence.PersistenceException;

public interface GetStockProductServicePort {

    InventoryResponse get(String productId, String token) throws PersistenceException, Exception.NotFoundException;
}
