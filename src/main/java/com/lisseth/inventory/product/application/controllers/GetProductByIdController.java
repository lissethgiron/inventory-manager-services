package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.GetProductByIdServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetProductByIdController {
    private final GetProductByIdServicePort getProductByIdService;

    @Operation(summary = "Service to get products by id")
    @GetMapping("/products/{productId}")
    public ResponseEntity<JsonApiResponse<ProductResponse>> getProduct(
            @PathVariable("productId") @Valid String productId
    ) {
        Product product = getProductByIdService.getById(productId);
        return ResponseController.success("product", product.getProductId(), new ProductResponse(product));
    }
}
