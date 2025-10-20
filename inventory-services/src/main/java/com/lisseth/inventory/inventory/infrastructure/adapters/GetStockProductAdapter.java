package com.lisseth.inventory.inventory.infrastructure.adapters;

import com.lisseth.inventory.inventory.domain.models.Inventory;
import com.lisseth.inventory.inventory.domain.ports.output.GetStockProductAdapterPort;
import com.lisseth.inventory.inventory.infrastructure.entities.InventoryEntity;
import com.lisseth.inventory.inventory.infrastructure.mappers.InventoryMapper;
import com.lisseth.inventory.inventory.infrastructure.repositories.InventoryRopository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetStockProductAdapter implements GetStockProductAdapterPort {

    private final InventoryRopository inventoryRopository;

    @Override
    public Optional<Inventory> find(String productId) throws PersistenceException {
        try {
            Optional<InventoryEntity> optionalInventoryEntity = inventoryRopository.findById(productId);;
            return optionalInventoryEntity.map(InventoryMapper::toDomain);
        } catch (jakarta.persistence.PersistenceException ex){
            log.error("find: Error getting inventory");
            throw new PersistenceException(ex);
        }
    }
}
