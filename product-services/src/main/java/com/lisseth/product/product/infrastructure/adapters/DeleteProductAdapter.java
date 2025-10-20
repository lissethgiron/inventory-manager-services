package com.lisseth.product.product.infrastructure.adapters;

import com.lisseth.product.product.domain.ports.output.DeleteProductAdapterPort;
import com.lisseth.product.product.infrastructure.Repositories.ProductRepository;
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
    public Boolean delete(String productId) throws PersistenceException {
        try {
            productRepository.deleteById(productId);
            return Boolean.TRUE;
        } catch (jakarta.persistence.PersistenceException e){
            log.error("save: Error deleting product");
            throw new PersistenceException(e);
        }
    }
}
