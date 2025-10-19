package com.lisseth.inventory.product.infrastructure.adapters;

import com.lisseth.inventory.product.domain.ports.output.DeleteProductAdapterPort;
import com.lisseth.inventory.product.infrastructure.Repositories.ProductRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteProductAdapter implements DeleteProductAdapterPort {

    private final ProductRepository productRepository;

    @Override
    public void delete(String productId) throws PersistenceException {
        try {
            productRepository.deleteById(productId);
        } catch (jakarta.persistence.PersistenceException e){
            log.error("save: Error deleting product");
            throw new PersistenceException(e);
        }
    }
}
