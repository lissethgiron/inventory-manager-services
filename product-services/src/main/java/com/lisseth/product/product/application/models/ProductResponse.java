package com.lisseth.product.product.application.models;

import com.lisseth.product.product.domain.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private String productId;
    private String name;
    private BigDecimal price;

    public ProductResponse(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
