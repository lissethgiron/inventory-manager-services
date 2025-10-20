package com.lisseth.product.product.domain.services;

import com.lisseth.product.common.domain.config.Exception;
import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.input.UpdateProductServicePort;
import com.lisseth.product.product.domain.ports.output.GetProductByIdAdapterPort;
import com.lisseth.product.product.domain.ports.output.UpdateProductAdapterPort;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductServicePort {

    private final GetProductByIdAdapterPort getProductByIdAdapter;
    private final UpdateProductAdapterPort updateProductAdapter;

    @Override
    public Product update(Product product) throws PersistenceException, Exception.NotFoundException {
        Optional<Product> OptionalProduct = getProductByIdAdapter.findById(product.getProductId());

        if (OptionalProduct.isEmpty()) {
            log.warn("The product no exist {}", product.getProductId());
            throw new Exception.NotFoundException("The product no exist " + product.getProductId());
        }

        return updateProductAdapter.save(product);
    }
}