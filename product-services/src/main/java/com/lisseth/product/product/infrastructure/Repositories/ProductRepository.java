package com.lisseth.product.product.infrastructure.Repositories;

import com.lisseth.product.product.infrastructure.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Page<ProductEntity> findAll(Pageable pageable);
}
