package com.lisseth.product.product.domain.services;

import com.lisseth.product.common.domain.config.Exception;
import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.input.GetProductByIdServicePort;
import com.lisseth.product.product.domain.ports.output.GetProductByIdAdapterPort;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetProductByIdService implements GetProductByIdServicePort {

    private final GetProductByIdAdapterPort getProductByIdAdapter;

    @Override
    public Product getById(String productId) throws PersistenceException, Exception.NotFoundException {
        Optional<Product> OptionalProduct = getProductByIdAdapter.findById(productId);

        if (OptionalProduct.isEmpty()) {
            log.warn("The product no exist {}", productId);
            throw new Exception.NotFoundException("The product no exist " +  productId);
        }

        return OptionalProduct.get();
    }
}
