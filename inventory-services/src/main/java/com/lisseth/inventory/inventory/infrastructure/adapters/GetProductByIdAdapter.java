package com.lisseth.inventory.inventory.infrastructure.adapters;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.inventory.domain.models.Product;
import com.lisseth.inventory.inventory.domain.ports.output.GetProductByIdAdapterPort;
import com.lisseth.inventory.inventory.infrastructure.models.ProductResponse;
import com.lisseth.inventory.inventory.infrastructure.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetProductByIdAdapter implements GetProductByIdAdapterPort {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> get(String productId, String token) {
        ProductResponse productResponse = productRepository.getProductById(productId, token);

        if (Objects.isNull(productResponse)){
            log.warn("productResponse is mull");
            return Optional.empty();
        }

        return Optional.ofNullable(Product.builder()
                        .productId(productResponse.getData().getAttributes().getProductId())
                        .name(productResponse.getData().getAttributes().getName())
                        .price(productResponse.getData().getAttributes().getPrice())
                        .build());
    }
}
