package com.lisseth.inventory.product.application.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductRequest  implements Serializable {

    @NotBlank(message = "must not be empty or null")
    private String name;

    @NotNull(message = "must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
    private BigDecimal price;
}
