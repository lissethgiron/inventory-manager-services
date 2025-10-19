package com.lisseth.inventory.product.infrastructure.adapters;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.output.GetProductByIdAdapterPort;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import com.lisseth.inventory.product.infrastructure.entities.ProductEntity;
import com.lisseth.inventory.product.infrastructure.mappers.ProductMapper;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetProductByIdAdapter implements GetProductByIdAdapterPort {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(String productId) throws PersistenceException {
        try {
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);;
            return optionalProductEntity.map(ProductMapper::toDomain);
        } catch (jakarta.persistence.PersistenceException ex){
            log.error("findById: Error getting product");
            throw new PersistenceException(ex);
        }
    }
}