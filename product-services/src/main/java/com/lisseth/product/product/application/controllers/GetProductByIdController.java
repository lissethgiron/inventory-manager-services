package com.lisseth.product.product.application.controllers;

import com.lisseth.product.common.application.controllers.ResponseController;
import com.lisseth.product.common.application.models.JsonApiResponse;
import com.lisseth.product.product.application.models.ProductResponse;
import com.lisseth.product.product.domain.models.Product;
import com.lisseth.product.product.domain.ports.input.GetProductByIdServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.lisseth.product.common.application.controllers.util.JwtUtil.checkAuth;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetProductByIdController {
    private final GetProductByIdServicePort getProductByIdService;

    @Operation(summary = "Service to get products by id")
    @GetMapping("/products/{productId}")
    public ResponseEntity<JsonApiResponse<ProductResponse>> getProduct(
            @RequestHeader(value="Authorization") String authorization,
            @PathVariable("productId") @Valid String productId
    ) {
        checkAuth(authorization);
        Product product = getProductByIdService.getById(productId);
        return ResponseController.success("product", product.getProductId(), new ProductResponse(product));
    }
}
