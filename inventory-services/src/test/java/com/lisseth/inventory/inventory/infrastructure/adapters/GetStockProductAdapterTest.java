package com.lisseth.inventory.inventory.infrastructure.adapters;

import com.lisseth.inventory.inventory.domain.models.Inventory;
import com.lisseth.inventory.inventory.infrastructure.entities.InventoryEntity;
import com.lisseth.inventory.inventory.infrastructure.repositories.InventoryRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetStockProductAdapterTest {

    private GetStockProductAdapter getStockProductAdapter;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getStockProductAdapter = new GetStockProductAdapter(inventoryRepository);
    }

    @Test
    void shouldGetInventoryByProductIdWhenFindThenReturnInventory() {
        var uuid = UUID.randomUUID().toString();
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setInventoryId(uuid);
        inventoryEntity.setProductId(uuid);
        inventoryEntity.setQuantity(4L);

        final var productEntityOptional = Optional.of(inventoryEntity);

        when(inventoryRepository.findByProductId(uuid)).thenReturn(productEntityOptional);
        final Optional<Inventory> productResponse = getStockProductAdapter.find(uuid);
        assertNotNull(productResponse);
        assertTrue(productResponse.isPresent());
        assertEquals(productResponse.get().getProductId(), productEntityOptional.get().getProductId());
    }

    @Test
    void  shouldThrowExceptionWhenFindByIdThenThrowPersistenceException() {
        var productId = UUID.randomUUID().toString();
        when(inventoryRepository.findByProductId(productId))
                .thenThrow(jakarta.persistence.PersistenceException.class);
        assertThrows(PersistenceException.class, () -> getStockProductAdapter.find(productId));
    }
  
}