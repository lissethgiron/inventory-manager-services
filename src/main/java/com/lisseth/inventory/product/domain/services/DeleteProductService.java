package com.lisseth.inventory.product.domain.services;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.DeleteProductServicePort;
import com.lisseth.inventory.product.domain.ports.output.DeleteProductAdapterPort;
import com.lisseth.inventory.product.domain.ports.output.GetProductByIdAdapterPort;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductServicePort {

    private final GetProductByIdAdapterPort getProductByIdAdapter;
    private final DeleteProductAdapterPort deleteProductAdapter;

    @Override
    public Boolean delete(String productId) throws PersistenceException, Exception.NotFoundException {
        Optional<Product> OptionalProduct = getProductByIdAdapter.findById(productId);

        if (OptionalProduct.isEmpty()) {
            log.warn("The product no exist {}", productId);
            throw new Exception.NotFoundException("The product no exist " + productId);
        }

        deleteProductAdapter.delete(productId);

        return Boolean.TRUE;
    }

}
