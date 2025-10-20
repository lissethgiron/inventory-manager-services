package com.lisseth.product.product.infrastructure.adapters;

import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.output.UpdateProductAdapterPort;
import com.lisseth.product.product.infrastructure.Repositories.ProductRepository;
import com.lisseth.product.product.infrastructure.mappers.ProductMapper;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateProductAdapter implements UpdateProductAdapterPort {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) throws PersistenceException {
        try {
            productRepository.save(Objects.requireNonNull(ProductMapper.toEntityUpdate(product)));
            return product;
        } catch (jakarta.persistence.PersistenceException e){
            log.error("save: Error updating product");
            throw new PersistenceException(e);
        }
    }
}
