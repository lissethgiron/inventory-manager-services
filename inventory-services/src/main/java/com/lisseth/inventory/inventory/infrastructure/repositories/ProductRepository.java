package com.lisseth.inventory.inventory.infrastructure.repositories;

import com.lisseth.inventory.common.domain.config.Exception;
import com.lisseth.inventory.inventory.infrastructure.models.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Repository
public class ProductRepository {

    public ProductResponse getProductById(String productId, String token) {
        log.info("getProductById: {} {}", productId, token);
        WebClient webClient = WebClient.builder()
                .baseUrl("http://product-services:8080/product")
                .build();
        try {
            return webClient
                    .get()
                    .uri("/products/{id}", productId)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(ProductResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error, connection problems with the product microservice: {}", ex.getMessage());
            throw mapWebClientException(ex);
        }
    }

    private RuntimeException mapWebClientException(WebClientResponseException ex) {
        return switch (ex.getStatusCode().value()) {
            case 400 -> new Exception.BadRequestException(ex.getMessage());
            case 404 -> new Exception.NotFoundException("Product not found in db product");
            case 401 -> new Exception.UnauthorizedException(ex.getMessage());
            default -> new jakarta.persistence.PersistenceException(ex.getMessage());
        };
    }
}
