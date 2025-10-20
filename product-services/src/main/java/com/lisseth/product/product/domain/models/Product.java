package com.lisseth.product.product.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private String productId;
    private String name;
    private BigDecimal price;
}
