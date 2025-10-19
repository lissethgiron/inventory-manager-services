package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.domain.services.DeleteProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteProductController {

    private final DeleteProductService deleteProductService;

    @Operation(summary = "Service to get products by id")
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<JsonApiResponse<Boolean>> deleteProduct(
            @PathVariable("productId") @Valid String productId
    ) {
        Boolean response = deleteProductService.delete(productId);
        log.info("product deleted: {}, {}", response, productId);
        return ResponseController.success("product", productId, response);
    }
}
