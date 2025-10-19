package com.lisseth.inventory.product.infrastructure.adapters;

import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.output.GetListProductsAdapterPort;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import com.lisseth.inventory.product.infrastructure.mappers.ProductMapper;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetListProductsAdapter implements GetListProductsAdapterPort {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAll(int page, int size) throws PersistenceException {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return productRepository.findAll(pageable).map(ProductMapper::toDomain);
        } catch (jakarta.persistence.PersistenceException ex){
            log.error("findAll: Error getting products");
            throw new PersistenceException(ex);
        }
    }
}
