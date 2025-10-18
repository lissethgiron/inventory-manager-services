package com.lisseth.inventory.product.infrastructure.adapters;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.output.CreateProductAdapterPort;
import com.lisseth.inventory.product.infrastructure.entities.ProductEntity;
import com.lisseth.inventory.product.infrastructure.mappers.ProductMapper;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateProductAdapter implements CreateProductAdapterPort {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        try {
            ProductEntity productEntity = ProductMapper.toEntity(product);
            return ProductMapper.toDomain(productRepository.save(productEntity));
        } catch (jakarta.persistence.PersistenceException e){
            log.error("save: Error guardando en BD");
            throw new PersistenceException(e);
        }
    }
}
