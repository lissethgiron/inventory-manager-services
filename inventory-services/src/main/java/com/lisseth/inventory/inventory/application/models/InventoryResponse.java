package com.lisseth.inventory.inventory.application.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryResponse {

    private String productId;
    private String name;
    private Long quantity;
    private BigDecimal price;
}
