package com.lisseth.inventory.inventory.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "inventory")
@ToString
public class InventoryEntity implements Serializable {

    @Id
    @Column(name = "inventory_id", length = 50, nullable = false)
    private String inventoryId;

    @Column(name = "product_id", length = 50, nullable = false)
    private String productId;

    @Column(name = "quantity")
    private Long quantity;
}
