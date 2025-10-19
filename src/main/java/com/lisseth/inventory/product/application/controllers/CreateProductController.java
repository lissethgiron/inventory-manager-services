package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductRequest;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.models.Product;
import com.lisseth.inventory.product.domain.ports.input.CreateProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.lisseth.inventory.common.application.controllers.util.JwtUtil.checkAuth;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateProductController {

    private final CreateProductServicePort createProductService;

    @Operation(summary = "Service to create products.")
    @PostMapping("/products")
    public ResponseEntity<JsonApiResponse<ProductResponse>> createProduct(
            @RequestHeader(value="Authorization") String authorization,
            @Valid @RequestBody ProductRequest productRequest
    ) {
        checkAuth(authorization);
        Product product = createProductService.create(
                Product.builder()
                        .name(productRequest.getName())
                        .price(productRequest.getPrice())
                        .build());

        return ResponseController.created("product", product.getProductId(), new ProductResponse(product));
    }
}
