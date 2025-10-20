package com.lisseth.inventory.inventory.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory implements Serializable {

    private String inventoryId;
    private String productId;
    private Long quantity;
}
