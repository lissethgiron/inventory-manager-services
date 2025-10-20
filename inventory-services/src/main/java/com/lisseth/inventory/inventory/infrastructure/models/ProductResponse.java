package com.lisseth.inventory.inventory.infrastructure.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private Product attributes;

        @Getter
        @Setter
        public static class Product {
            private String productId;
            private String name;
            private BigDecimal price;
        };
    };
}
