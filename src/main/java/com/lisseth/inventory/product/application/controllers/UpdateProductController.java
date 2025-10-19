package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductRequest;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.UpdateProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateProductController {

    private final UpdateProductServicePort updateProductService;

    @Operation(summary = "Service to create products.")
    @PutMapping("/products/{productId}")
    public ResponseEntity<JsonApiResponse<ProductResponse>> updateProduct(
            @PathVariable("productId") @Valid String productId,
            @Valid @RequestBody ProductRequest productRequest
    ) {

        Product product = updateProductService.update(
                Product.builder()
                        .productId(productId)
                        .name(productRequest.getName())
                        .price(productRequest.getPrice())
                        .build());

        return ResponseController.success("product", product.getProductId(), new ProductResponse(product));
    }
}
